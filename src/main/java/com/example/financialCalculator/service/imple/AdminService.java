package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.repository.IAdminRepository;
import com.example.financialCalculator.repository.IUserRepository;
import com.example.financialCalculator.service.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;
    private final IUserRepository userRepository;

    public AdminService(IAdminRepository adminRepository, IUserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Admin createAdmin(Admin admin) throws UserAlreadyExistsException {

        Boolean existAdmin = userRepository.existsByNumIdentification(admin.getNumIdentification());

        if(existAdmin){
            throw new UserAlreadyExistsException("Admin with this identification already exists");
        }else {
            return this.adminRepository.save(admin);
        }

    }

    @Override
    public List<Admin> findAllAdmin() {
        return (List<Admin>) this.adminRepository.findAll();
    }

    @Override
    public Admin findAdminById(Long id) throws UserNotFound {
        return this.adminRepository.findById(id)
                .orElseThrow(()->new UserNotFound("not found"));
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
    public void deleteAdminById(Long id) throws UserNotFound {
       findAdminById(id);
       this.adminRepository.deleteById(id);
    }

    @Override
    public Boolean longinPasswordAndEmail(String email, String password) {
        return this.adminRepository.existsByEmailAndPassword(email,password);
    }

    @Override
    public Boolean loginPasswordAndUsername(String username, String password) {
        return this.adminRepository.existsByUsernameAndPassword(username,password);
    }



}
