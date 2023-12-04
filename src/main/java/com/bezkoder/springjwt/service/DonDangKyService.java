package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.config.NguoiDungDetail;
import com.bezkoder.springjwt.config.NguoiDungService;
import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.DonDangKys;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.DaoTrangRepo;
import com.bezkoder.springjwt.repository.DonDangKyRepo;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@ControllerAdvice
@Service
public class DonDangKyService extends ResponseEntityExceptionHandler implements InterfaceDonDangKy  {
    @Autowired
    DonDangKyRepo donDangKyRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DaoTrangRepo daoTrangRepo;

//    @Override
//    public ResponseEntity<?> addDonDangKy(DonDangKys donDangKy)  {
//        Optional<User> currentUser = userRepository.findById(getCurrentUserId());
//        if (currentUser == null) {
//            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User chua dang nhap"));
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//        }
////        donDangKy.setUser(currentUser.get());
//        donDangKy.setUserId(currentUser.get().getId());
//        // Kiểm tra xem đã có đơn đăng ký nào của cùng một phật tử và cùng một buổi đạo tràng hay chưa
//        DaoTrangs daoTrang = donDangKy.getDaoTrangs();
//        if (daoTrang != null && currentUser.get().getId() == donDangKy.getUser().getId()) {
//            List<DonDangKys> existingDonDangKys = donDangKyRepo.findByUserIdAndDaoTrangId(currentUser.get().getId(), daoTrang.getDaoTrangId());
//            if (!existingDonDangKys.isEmpty()) {
//                ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User da tao don dang ky cho buoi dao trang nay"));;
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//            }
//        }
//
//        Optional<DaoTrangs> daoTrangOptional = daoTrangRepo.findById((donDangKy.getDaoTrangId()));
//        if(daoTrangOptional.isEmpty()){
//            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("Dao trang khong ton tai"));
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }
////        donDangKy.setDaoTrangs(daoTrangOptional.get());
//        donDangKy.setDaoTrangId(daoTrangOptional.get().getDaoTrangId());
//        donDangKy.setNgayGuiDon(LocalDateTime.now());
//        donDangKy.setTrangThaiDon(false);
//        donDangKyRepo.save(donDangKy);
//        return ResponseEntity.ok(donDangKy);
//    }
    @Override
    public ResponseEntity<?> addDonDangKy(int daoTrangId)  {
        Optional<User> currentUser = userRepository.findById(getCurrentUserId());
        if (currentUser == null) {
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User chua dang nhap"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
        DonDangKys donDangKy = new DonDangKys();
        Optional<DaoTrangs> daoTrangsOptional = daoTrangRepo.findById(daoTrangId);
        // Kiểm tra xem đã có đơn đăng ký nào của cùng một phật tử và cùng một buổi đạo tràng hay chưa
        // Kiem tra xem phat tu da dang ky dao trang nay bao gio hay chua
        if (!daoTrangsOptional.isEmpty() ) {
            List<DonDangKys> existingDonDangKys = donDangKyRepo.findByUserIdAndDaoTrangId(currentUser.get().getId(), daoTrangId);
            if (!existingDonDangKys.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User da tao don dang ky cho buoi dao trang nay"));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            } else {
                donDangKy.setUserId(currentUser.get().getId());
                donDangKy.setDaoTrangId(daoTrangId);
                donDangKy.setNgayGuiDon(LocalDateTime.now());
                donDangKy.setTrangThaiDon(false);
                donDangKyRepo.save(donDangKy);
                return ResponseEntity.ok(donDangKy);
            }
        }else{
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("Dao trang khong ton tai"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        }
    }

    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // Trả về giá trị null nếu người dùng chưa đăng nhập hoặc chưa xác thực
            return null;
        }
        String username = authentication.getName();
        NguoiDungService nguoiDungService = new NguoiDungService(userRepository);
        NguoiDungDetail currentUser = nguoiDungService.loadUserByUsername(username);
        // Lấy thông tin người đăng nhập từ đối tượng NguoiDungDetail
        int userId = currentUser.getId();
        return userId;
    }


    @Override
    public ResponseEntity<?> deleteDonDangKy(int id) {
        Optional<DonDangKys> donDangKysOptional = donDangKyRepo.findById(id);
        if(donDangKysOptional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User khong ton tai"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
            donDangKyRepo.delete(donDangKysOptional.get());
            return ResponseEntity.ok("Delete don dang ky thanh cong");
    }

    @Override
    public ResponseEntity<?> updateDonDangKy(DonDangKys newDonDangKy) {
        Optional<User> currentUser = userRepository.findById(getCurrentUserId());
        if(currentUser == null){
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("User khong xac dinh"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        Optional<DonDangKys> donDangKysOptional = donDangKyRepo.findById(newDonDangKy.getDonDangKyId());
        if(donDangKysOptional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("Don dang ky khong ton tai"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else{
            DonDangKys donDangKys = donDangKyRepo.findById(newDonDangKy.getDonDangKyId()).orElse(null);
            donDangKys.setNgayGuiDon(newDonDangKy.getNgayGuiDon());
            donDangKys.setDaoTrangId(newDonDangKy.getDaoTrangId());
            return ResponseEntity.ok("Update don dang ky thanh cong");
        }
    }

    @Override
    public Page<DonDangKys> donDangKySortingPaging(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null != sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        }else{
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "ngayXuLy");
        }
        return donDangKyRepo.findAll(pageable);
    }

    @Override
    public ResponseEntity<?> duyetDon(int id) {
        Optional<DonDangKys> donDangKysOptional = donDangKyRepo.findById(id);
        if(donDangKysOptional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.UNAUTHORIZED, new Throwable("Don dang ky khong ton tai"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        DonDangKys donDangKy = donDangKysOptional.get();
        //Neu don dang ky chua ai duyet
        if(!donDangKy.getTrangThaiDon()){
            Optional<User> nguoiXuLy = userRepository.findById(getCurrentUserId());
            boolean newTrangThaiDon = true;
            LocalDateTime ngayXuLy = LocalDateTime.now();
            //Cap nhat thong tin cho don dang ky
            donDangKy.setNgayXuLy(ngayXuLy);
            donDangKy.setTrangThaiDon(newTrangThaiDon);
            donDangKy.setNguoiXuLy(nguoiXuLy.get().getId());
            DaoTrangs daoTrangs = donDangKy.getDaoTrangs();
            daoTrangs.setSoThanhVienThamGia(daoTrangs.getSoThanhVienThamGia() + 1);

        }
        return ResponseEntity.ok("Duyet don dang ky thanh cong");
    }
}
