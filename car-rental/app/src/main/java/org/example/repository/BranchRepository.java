package org.example.repository;

import org.example.model.Branch;
import java.util.Map;
import java.util.HashMap;

public class BranchRepository {
    private final Map<String,Branch> branchMap = new HashMap<>();
    
    public void addBranch(Branch branch)
    {
        branchMap.put(branch.getId(), branch);
    }

    public Branch getBranch(String id)
    {
        return branchMap.get(id);
    }
    
}
