package family.tree.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class TreeNode {
    public Long id;
    public String fullName;
    public String birthDate;
    public String gender;
    public List<TreeNode> children = new ArrayList<>();
}