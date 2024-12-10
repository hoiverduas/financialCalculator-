package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.repository.IAdminRepository;
import com.example.financialCalculator.service.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public Admin createAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    @Override
    public List<Admin> findAllAdmin() {
        return (List<Admin>) this.adminRepository.findAll();
    }

    @Override
    public Admin findAdminById(Long id) {
        return this.adminRepository.findById(id)
                .orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Admin updateAdmin(Admin admin) {

        Optional<Admin> optionalAdmin = adminRepository.findById(admin.getId());

        if (optionalAdmin.isPresent()){

            Admin existAdmin = optionalAdmin.get();

            existAdmin.setEmail(admin.getEmail());
            existAdmin.setPassword(admin.getPassword());
            existAdmin.setFullName(admin.getFullName());
            existAdmin.setPhone(admin.getPhone());
            existAdmin.setAreaOfResponsibility(admin.getAreaOfResponsibility());

            return this.adminRepository.save(existAdmin);
        }else {
            throw new RuntimeException("not found");
        }
    }

    @Override
    public void deleteAdminById(Long id) {
       findAdminById(id);
       this.adminRepository.deleteById(id);
    }




}
