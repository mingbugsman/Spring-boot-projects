package com.PhoneInventoryManager.PhoneInventory.Service;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.SpecificationRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Category;
import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import com.PhoneInventoryManager.PhoneInventory.Entity.Specification;
import com.PhoneInventoryManager.PhoneInventory.Mapper.PhoneMapper;
import com.PhoneInventoryManager.PhoneInventory.Mapper.SpecificationMapper;
import com.PhoneInventoryManager.PhoneInventory.Repository.PhoneCategoryRepository;
import com.PhoneInventoryManager.PhoneInventory.Repository.PhoneRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhoneService {
    PhoneRepository phoneRepository;
    PhoneCategoryRepository phoneCategoryRepository;
    SpecificationMapper specificationMapper;
    PhoneMapper phoneMapper;
    public PhoneDTO createNewPhone(PhoneRequest request) throws Exception {
        // check exist
        if (phoneRepository.existsByModelAndBrand(request.getModel(), request.getBrand())) {
            throw new Exception("phone is existed");
        }
        Phone phone = phoneMapper.toPhone(request);
        // check category exist ?
        Category category = phoneCategoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new Exception("not found category "));

        phone.setCategory(category);

        log.info(phone.toString());
        SpecificationRequest specificationRequest = request.getSpecification();
        Specification specification = specificationMapper.toSpecification(specificationRequest);
        phone.setSpecification(specification);
        specification.setPhone(phone);
        phone = phoneRepository.save(phone);

        return phoneMapper.toPhoneResponse(phone);
    }
}
