package com.PhoneInventoryManager.PhoneInventory.Service;

import com.PhoneInventoryManager.PhoneInventory.DTO.Request.PhoneImageRequest;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.Phone.PhoneDTO;
import com.PhoneInventoryManager.PhoneInventory.DTO.Response.PhoneImage.PhoneImageDTO;
import com.PhoneInventoryManager.PhoneInventory.Entity.Phone;
import com.PhoneInventoryManager.PhoneInventory.Entity.PhoneImage;
import com.PhoneInventoryManager.PhoneInventory.Mapper.PhoneImageMapper;
import com.PhoneInventoryManager.PhoneInventory.Mapper.PhoneMapper;
import com.PhoneInventoryManager.PhoneInventory.Repository.PhoneImageRepository;
import com.PhoneInventoryManager.PhoneInventory.Repository.PhoneRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhoneImageService {
    PhoneImageRepository phoneImageRepository;
    PhoneRepository phoneRepository;
    PhoneMapper phoneMapper;
    PhoneImageMapper phoneImageMapper;

    public PhoneDTO findPhoneWithDetails(String phone_id) {
        Phone phone = phoneRepository.findPhoneWithDetails(phone_id);
        if (phone != null) {
            System.out.println("phone is existed");
        }
        PhoneDTO response = phoneMapper.toPhoneResponse(phone);
        response.setImages(getImageDTOList(phone_id));
        return response;
    }
    private List<PhoneImageDTO> getImageDTOList(String phone_id) {
        return phoneImageRepository.findByPhoneId(phone_id).stream().map(phoneImageMapper::toPhoneImageDTO).toList();
    }
    public void createImages(String phone_id, MultipartFile[] files) throws IOException {
        Phone phone = phoneRepository.findById(phone_id).orElseThrow(() -> new RuntimeException("pHONE NOT found"));
        for (int i = 0; i < files.length; i++) {
            var file = files[i];
            var data = file.getBytes();
            boolean isPrimary = (i == 0); // First image will be primary
            PhoneImageRequest request = PhoneImageRequest.builder()
                    .dataImage(data)
                    .build();
            System.out.println(request.toString());

            saveImage(request, phone, isPrimary);
        }
    }

    private void saveImage(PhoneImageRequest request, Phone phone, boolean isPrimary) {

        PhoneImage phoneImage = phoneImageMapper.toPhoneImage(request);
        phoneImage.setPhone(phone);
        phoneImage.setPrimary(isPrimary);
        phoneImageRepository.save(phoneImage);
        phoneImageRepository.flush();
    }
}
