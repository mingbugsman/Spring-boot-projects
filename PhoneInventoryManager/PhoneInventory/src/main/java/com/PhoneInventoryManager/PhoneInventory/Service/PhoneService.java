package com.PhoneInventoryManager.PhoneInventory.Service;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Request.SpecificationRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneSummaryDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhoneService {
    PhoneRepository phoneRepository;
    PhoneCategoryRepository phoneCategoryRepository;
    SpecificationMapper specificationMapper;
    PhoneMapper phoneMapper;

    /**
     * @param phone_id phone id to retrieve specific information
     * @return {@code PhoneDTO} After completing the query
     *
     */
    public PhoneDTO getPhone(String phone_id) throws Exception {
        Phone phone = phoneRepository.findPhoneWithDetails(phone_id);
        if (phone == null) {
           throw new Exception("Not found phone with id " + phone_id);
        }
        return phoneMapper.toPhoneResponse(phone);
    }
    /**
     * @return the list {@code PhoneDTO} After completing the query all products
     */
    public List<PhoneDTO> getAllPhones() {
        return phoneRepository.findAll().stream().map(phoneMapper::toPhoneResponse).toList();
    }

    /**
     *
     * @param lastCreatedAt createdAt value of previous page footer record (null if first page)
     * @param size number of records per page
     * @return phoneSummaryDTO
     */
    public Page<PhoneSummaryDTO> getNextPhonesPage(LocalDateTime lastCreatedAt, int size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return phoneRepository.findNextPage(lastCreatedAt == null ? LocalDateTime.now() : lastCreatedAt, pageable)
                .map(phoneMapper::toPHONE_SUMMARY_DTO);

    }

    /**
     * @return the list {@code PhoneDTO} After completing the query, the products belong to a certain category
     * and have the price range that the user wants
     */
    public List<PhoneDTO> findByCategoryAndPriceRange(String categoryName, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Phone> response = phoneRepository.findByCategoryAndPriceRange(categoryName, minPrice, maxPrice) ;
        if (response.isEmpty()) {
            return List.of();
        }
        return response.stream().map(phoneMapper::toPhoneResponse).toList();
    }

    /**
     * @return the list {@code PhoneDTO} after completing the querying the best-selling product in the previous week
     */
    public List<PhoneDTO> findPhonesCreatedLastWeek() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusDays(7);
         List<Phone> response = phoneRepository.findPhonesCreatedLastMonth(oneMonthAgo);
         return response == null ? List.of() : response.stream().map(phoneMapper::toPhoneResponse).toList();
    }

    /**
     * @return the list {@code PhoneDTO} after completing the querying the best-selling product
     */
    public List<PhoneDTO> getTopSellingPhones() {
        return phoneRepository.findTop10ByOrderBySoldQuantityDesc().stream().map(phoneMapper::toPhoneResponse).toList();
    }

    /**
     * @return the list {@code PhoneDTO} after completing the querying the best-selling product in the previous month
     */
    public List<PhoneDTO> getTopSellingPhonesLastMonth() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusDays(30);
        return phoneRepository.findTopSellingPhonesLastMonth(oneMonthAgo).stream().map(phoneMapper::toPhoneResponse).toList();
    }

    /**
     *
     * @param request  the {@code PhoneRequest} containing created details for the phone
     * @return the created {@code PhoneDTO} after the phone has been saved
     * @throws Exception if the phone or the specified category cannot be found
     *
     */
    public PhoneDTO createNewPhone(PhoneRequest request) throws Exception {
        // check exist
        if (phoneRepository.existsByModelAndBrand(request.getModel(), request.getBrand())) {
            throw new Exception("phone is existed");
        }
        // mapping
        Phone phone = phoneMapper.toCreationPhone(request);
        // check category exist ?
        Category category = phoneCategoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new Exception("not found category "));

        // set value for ignored attributes
        phone.setCategory(category);

        SpecificationRequest specificationPhoneRequest = request.getSpecification();
        Specification specification = specificationMapper.toSpecification(specificationPhoneRequest);

        phone.setSpecification(specification);
        specification.setPhone(phone);

        phone = phoneRepository.save(phone);
        return phoneMapper.toPhoneResponse(phone);
    }

     /**
      *
      * @param phone_id the ID of the phone to update
      * @param request  the {@code PhoneRequest} containing updated details for the phone
      * @return the updated {@code PhoneDTO} after the phone has been saved
      * @throws Exception if the phone or the specified category cannot be found
      *
     */
    public PhoneDTO updatePhone(String phone_id, PhoneRequest request) throws Exception {
        Phone phone = phoneRepository.findById(phone_id).orElseThrow(() -> new Exception("Not found phone"));

        // mapping
        phoneMapper.updatePhone(phone, request);
        Category category = phoneCategoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new Exception("not found category "));

        // set value for ignored attributes
        phone.setCategory(category);
        phone.setUpdatedAt(LocalDateTime.now());

        Specification specification = phone.getSpecification(); // Lấy specification cũ
        if (specification == null) {
            specification = specificationMapper.toSpecification(request.getSpecification()); // Nếu chưa có, tạo mới
        } else {
            specificationMapper.updateSpecification(specification, request.getSpecification()); // Update if it already is existed
        }

        phone.setSpecification(specification);

        // Update !!!
        phone = phoneRepository.save(phone);
        return  phoneMapper.toPhoneResponse(phone);
    }

    /**
     * Deletes the phone with the specified ID.
     *
     * @param phone_id The ID of the phone to delete.
     * @throws Exception if no phone is found with the given ID
     * .
     */
    public void deletePhone(String phone_id) throws Exception {
        if (!phoneRepository.existsById(phone_id)) {
            throw new Exception("not found phone");
        }
        phoneRepository.deleteById(phone_id);
    }




}
