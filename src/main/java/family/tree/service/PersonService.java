package family.tree.service;

import family.tree.dto.TreeNode;
import family.tree.entity.Person;
import family.tree.repository.PersonRepository;
import family.tree.repository.PersonSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository repo;

    public PersonService(PersonRepository repo) { this.repo = repo; }

    public Person create(Person p) { return repo.save(p); }
    public Optional<Person> get(Long id) { return repo.findById(id); }
    public Person update(Long id, Person p) {
        p.setId(id);
        return repo.save(p);
    }
    public void delete(Long id) { repo.deleteById(id); }

    public List<Person> listAll() { return repo.findAll(); }

    // Build forest (all roots) or tree from a specific root
    public List<TreeNode> buildForest() {
        List<Person> all = repo.findAll();
        Map<Long, Person> byId = all.stream().collect(Collectors.toMap(Person::getId, x->x));
        Map<Long, TreeNode> nodes = new HashMap<>();
        for (Person p : all) {
            TreeNode n = toNode(p);
            nodes.put(p.getId(), n);
        }
        // Attach children to parents (using motherId/fatherId)
        Set<Long> childIds = new HashSet<>();
        for (Person p : all) {
            TreeNode node = nodes.get(p.getId());
            if (p.getMotherId() != null && nodes.containsKey(p.getMotherId())) {
                nodes.get(p.getMotherId()).children.add(node);
                childIds.add(p.getId());
            }
            if (p.getFatherId() != null && nodes.containsKey(p.getFatherId())) {
                nodes.get(p.getFatherId()).children.add(node);
                childIds.add(p.getId());
            }
        }
        // Roots are persons who are not anyone's child
        List<TreeNode> roots = new ArrayList<>();
        for (Person p : all) {
            if (!childIds.contains(p.getId())) {
                roots.add(nodes.get(p.getId()));
            }
        }
        return roots;
    }

    public TreeNode buildTreeFromRoot(Long rootId) {
        List<TreeNode> forest = buildForest();
        // search root
        Deque<TreeNode> stack = new ArrayDeque<>(forest);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if (n.id.equals(rootId)) return n;
            stack.addAll(n.children);
        }
        return null;
    }

    private TreeNode toNode(Person p) {
        TreeNode n = new TreeNode();
        n.id = p.getId();
        n.fullName = p.getFullName();
        n.birthDate = p.getBirthDate() == null ? null : p.getBirthDate().toString();
        n.gender = p.getGender();
        return n;
    }

    public List<Person> search(String name, String birthDate , String gender) {

            Specification<Person> spec = Specification.where(null);

            spec = spec.and(PersonSpecifications.nameContains(name));
            spec = spec.and(PersonSpecifications.birthDateEquals(birthDate));
            spec = spec.and(PersonSpecifications.genderEquals(gender));

            return repo.findAll(spec);
        }

}
