//package com.fluffypets.services.impl;
//
//import com.fluffypets.services.CategoryService;
//import org.junit.Before;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class CategoryServiceImplTest {
//    private CategoryService service = mock(CategoryServiceImpl.class);
//
//    @Before
//    public void setUp() {
//        when(service.getAll()).thenReturn(new ArrayList<Car>());
//        when(service.getById(11)).thenReturn(new Car.Builder().build());
//        when(service.getById(12)).thenReturn(null);
//        when(service.getById(null)).thenReturn(null);
//        when(service.getById(13)).thenThrow(PersistException.class);
//        when(service.getCount()).thenReturn(10);
//        when(service.getItemsPerPage()).thenReturn(340);
//        when(service.getPart(1)).thenReturn(new ArrayList<Car>() {{
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//        }});
//        when(service.getPart(2)).thenReturn(new ArrayList<Car>() {{
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//        }});
//        when(service.getPart(3)).thenReturn(new ArrayList<Car>());
//        when(service.getItemsPerPage()).thenReturn(15);
//        when(service.getAvailableCars()).thenReturn(new ArrayList<Car>(){{
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//            add(new Car.Builder().build());
//        }});
//        when(service.getByBrandAndModel("Brand", "Model")).thenReturn(new Car.Builder().build());
//        when(service.getByBrandAndModel("dummy", "dummy")).thenReturn(null);
//    }
//
//}