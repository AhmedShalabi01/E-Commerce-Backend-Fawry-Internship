package com.example.couponapi;//package com.example.couponapi;
//
//import com.example.couponapi.entities.Coupon;
//import com.example.couponapi.entities.types.CouponType;
//import com.example.couponapi.models.CouponDTO;
//import com.example.couponapi.models.mappers.CouponMapper;
//import com.example.couponapi.repositories.ConsumptionRepository;
//import com.example.couponapi.repositories.CouponRepository;
//import com.example.couponapi.services.CouponServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.Spy;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class CouponServiceTest {
//
//    @Mock
//    private CouponRepository couponRepository;
//    @Mock
//    private ConsumptionRepository consumptionRepository;
//    @Spy
//    private CouponMapper couponMapper;
//    @InjectMocks
//    private CouponServiceImpl couponService;
//
//    // Fields for global use
//    private List<Coupon> couponList;
//    private List<CouponDTO> couponDTOList;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @BeforeEach
//    public void createCouponSamples() {
//
//        Coupon coupon1 = new Coupon();
//        coupon1.setId(1L);
//        coupon1.setCode("TEST1");
//        coupon1.setMaxUsages(10);
//        coupon1.setType(CouponType.FIXED);
//        coupon1.setDiscount(50.0);
//        coupon1.setExpiryDate(LocalDate.of(2024, 6, 12));
//
//        Coupon coupon2 = new Coupon();
//        coupon2.setId(2L);
//        coupon2.setCode("SUPERTEST2");
//        coupon2.setMaxUsages(100);
//        coupon2.setType(CouponType.PERCENTAGE);
//        coupon2.setDiscount(0.50);
//        coupon2.setExpiryDate(LocalDate.of(2024, 1, 1));
//
//        couponList = new ArrayList<>(List.of(coupon1, coupon2));
//
//        CouponDTO couponDTO1 = new CouponDTO();
//        couponDTO1.setId(1L);
//        couponDTO1.setCode("TEST1");
//        couponDTO1.setMaxUsages(10);
//        couponDTO1.setType(CouponType.FIXED);
//        couponDTO1.setDiscount(50.0);
//        couponDTO1.setExpiryDate(LocalDate.of(2024, 6, 12));
//
//        CouponDTO couponDTO2 = new CouponDTO();
//        couponDTO2.setId(2L);
//        couponDTO2.setCode("SUPERTEST2");
//        couponDTO2.setMaxUsages(100);
//        couponDTO2.setType(CouponType.PERCENTAGE);
//        couponDTO2.setDiscount(0.50);
//        couponDTO2.setExpiryDate(LocalDate.of(2024, 1, 1));
//
//        couponDTOList = new ArrayList<>(List.of(couponDTO1, couponDTO2));
//    }
//
//    @Test
//    public void findAllCouponsTest() {
//        // Arrange
//        when(couponRepository.findAllCoupons(false)).thenReturn(couponList);
//
//        // Act
//        List<CouponDTO> result = couponService.findAllCoupons(false);
//
//        // Assert
//        verify(couponRepository, times(1)).findAllCoupons(false);
//        verify(couponMapper, times(2)).toCouponDto(any());
//
//        System.out.println(result);
//        assertThat(result).isNotNull();
//        assertThat(result).hasSize(2);
////        assertThat(result).containsExactlyElementsOf(couponDTOList);
//    }
//
//
//}
