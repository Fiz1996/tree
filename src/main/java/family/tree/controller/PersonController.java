package family.tree.controller;


import family.tree.dto.TreeNode;
import family.tree.entity.Person;
import family.tree.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) { this.personService = personService; }

    @PostMapping("/person")
    public ResponseEntity<Person> create(@RequestBody Person p) {
        return ResponseEntity.ok(personService.create(p));
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person p) {
        return ResponseEntity.ok(personService.update(id, p));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        return personService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/people")
    public List<Person> listAll() { return personService.listAll(); }

    @GetMapping("/search")
    public List<Person> listPerson(@RequestParam(required = false) String name ,
                                   @RequestParam(required = false) String birthDate ,
                                   @RequestParam(required = false) String gender) {
        return personService.search(name,birthDate,gender);
    }

    @GetMapping("/tree")
    public List<TreeNode> getForest() { return personService.buildForest(); }

    @GetMapping("/tree/{rootId}")
    public ResponseEntity<TreeNode> getTree(@PathVariable Long rootId) {
        TreeNode root = personService.buildTreeFromRoot(rootId);
        if (root == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(root);
    }


}
