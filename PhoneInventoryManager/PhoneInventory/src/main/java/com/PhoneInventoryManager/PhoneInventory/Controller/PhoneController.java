package com.PhoneInventoryManager.PhoneInventory.Controller;


import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneSummaryDTO;
import com.PhoneInventoryManager.PhoneInventory.Service.PhoneImageService;
import com.PhoneInventoryManager.PhoneInventory.Service.PhoneService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/phones")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhoneController {

    PhoneService phoneService;
    PhoneImageService phoneImageService;

    @GetMapping("/{phone_id}")
    public ResponseEntity<PhoneDTO> getPhone(@PathVariable String phone_id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.getPhone(phone_id));
    }

    @GetMapping("/detail/{phone_id}")
    public ResponseEntity<PhoneDTO> getPhoneWithDetail(@PathVariable String phone_id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(phoneImageService.findPhoneWithDetails(phone_id));
    }


    @GetMapping
    public ResponseEntity<List<PhoneDTO>> getPhones() {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.getAllPhones());
    }

    @GetMapping("/next-page")
    public ResponseEntity<Page<PhoneSummaryDTO>> getNextPhonesPage(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime lastCreatedAt,
            @RequestParam(defaultValue = "10") int size
            ) {

        return ResponseEntity.ok( phoneService.getNextPhonesPage(lastCreatedAt, size));
    }

    @GetMapping("/category/{categoryName}/price")
    public ResponseEntity< List<PhoneDTO>> findByCategoryAndPriceRange(String categoryName, BigDecimal minPrice, BigDecimal maxPrice) {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.findByCategoryAndPriceRange(categoryName,minPrice, maxPrice));
    }


    @GetMapping("/recentPhones")
    public ResponseEntity<List<PhoneDTO>> findPhonesCreatedLastWeek() {
        return ResponseEntity.ok(phoneService.findPhonesCreatedLastWeek());
    }
    @GetMapping("/top-selling")
    public ResponseEntity<List<PhoneDTO>> getTopSellingPhones() {
        return ResponseEntity.ok(phoneService.getTopSellingPhones());
    }

    @GetMapping("/top-selling/month")
    public ResponseEntity<List<PhoneDTO>> getTopSellingPhonesLastMonth() {
        return ResponseEntity.ok(phoneService.getTopSellingPhonesLastMonth());
    }


    @PostMapping
    public ResponseEntity<PhoneDTO> createNewPhone(@RequestBody @Valid PhoneRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.createNewPhone(request));
    }


    @PutMapping("/{phone_id}")
        public ResponseEntity<PhoneDTO> updatePhone(@PathVariable String phone_id, @RequestBody @Valid PhoneRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(phoneService.updatePhone(phone_id, request));
    }

    @DeleteMapping("/{phone_id}")
    public ResponseEntity<String> deletePhone(@PathVariable String phone_id) throws Exception {
        phoneService.deletePhone(phone_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully delete phone");
    }


    // Creating Image
    @PostMapping("/{phoneId}/images")
    public ResponseEntity<String> uploadImages(@PathVariable String phoneId, @RequestParam("files")MultipartFile[] files) {
        try {
            // Lưu ảnh vào cơ sở dữ liệu
            phoneImageService.createImages(phoneId, files);
            return ResponseEntity.ok("Images uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading images: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
