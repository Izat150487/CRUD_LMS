package com.peaksoft.controller;

import com.peaksoft.entity.Company;
import com.peaksoft.entity.Student;
import com.peaksoft.service.CompanyService;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final StudentService studentService;

    @Autowired
    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }


    @GetMapping()
    public String getAllCompanies(Model model){
        List<Company>companies=companyService.getAllCompanies();
        model.addAttribute("companies",companies);
        return "company/companies";
    }
    @GetMapping("/addCompany")
    public String addCompany(Model model){
        model.addAttribute("company",new Company());
        return "company/addCompany";
    }
    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company")Company company){
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/{id}/updateCompany")
    public String updateCompany(@PathVariable("id")Long id,Model model){
        model.addAttribute("company",companyService.getCompanyById(id));
        return "company/updateCompany";
    }
    @PatchMapping("/{id}")
    public String saveUpdateCompany(@PathVariable("id")Long id,@ModelAttribute("company")Company company){
        companyService.updateCompany(company,id);
        return "redirect:/companies";
    }
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable("id")Long id){
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/companies";
    }
    @GetMapping("/getStudents/{companyId}")
    public String getStudents(@PathVariable("companyId")Long companyId,Model model){
        List<Student>students;
        students=studentService.getStudentsByCompany(companyId);
        model.addAttribute("students",students);
        model.addAttribute("size",students.size());
        return "company/getStudents";
    }
}
