//package com.example.lab2.controlers.rest;
//
//import com.example.lab2.service.FinanceService;
//import io.swagger.v3.oas.annotations.*;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/family_finances")
//
//public class MainController {
//
//    private final FinanceService service;
//
//    public MainController(FinanceService service) {
//        this.service = service;
//    }
//
//    @PostMapping("/users/{familyId}")
//    @Operation(summary = "Добавить пользователя в семью",
//            description = "Добавляет нового пользователя в семью",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<Void> addUser(@RequestBody UserDTO userDTO, @PathVariable Integer familyId) {
//        service.addUser(userDTO,familyId);
//        return ResponseEntity.status(201).build();
//    }
//
//    @PostMapping("/users")
//    @Operation(summary = "Создает пользователя",
//            description = "Создает пользователя и новый семейный бюджет",
//            responses = {
//                    @ApiResponse(responseCode = "201",
//                            description = "Успешное выполнение, создана новая запись"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<Void> addNewUser(@RequestBody UserDTO userDTO){
//        service.createNewFamily(userDTO);
//        return ResponseEntity.status(201).build();
//    }
//
//    @GetMapping("/users/{userId}")
//    @Operation(summary = "Получить пользователя",
//            description = "Получение пользователя по id",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){
//        UserDTO userDTO = service.getUser(userId);
//        return ResponseEntity.status(200).body(userDTO);
//    }
//    @DeleteMapping("/users/{userId}")
//    @Operation(summary = "Удалить пользователя",
//            description = "Удаление пользователя по id",
//            responses = {
//                    @ApiResponse(responseCode = "204",
//                            description = "Успешное выполнение,нет содержимого"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
//        service.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }
//    @PatchMapping("/users/{userId}")
//    @Operation(summary = "Обновить пользователя",
//            description = "Обновления пользователя по id",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<Void> updateUser(@PathVariable Integer userId){
//        service.updateUser(userId);
//        return ResponseEntity.status(200).build();
//    }
//
//    @PostMapping("/buys/{familyId}")
//    @Operation(summary = "Добовление покупки",
//            description = "Добавляет новые покупки семье",
//            responses = {
//                    @ApiResponse(responseCode = "201",
//                            description = "Успешное выполнение, покупка записана"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public ResponseEntity<Void> addBuy(@RequestBody BuyDTO buyDTO,@PathVariable Integer familyId) {
//        service.addBuy(buyDTO,familyId);
//        return ResponseEntity.status(201).build();
//    }
//
//    @GetMapping("/buys/{familyId}")
//    @Operation(summary = "Список покупок семьи",
//            description = "Возвращает список покупок по id семьи",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public  ResponseEntity<List<BuyDTO>> getBuys(@PathVariable Integer familyId){
//        return ResponseEntity.status(200).body(service.getBuys(familyId));
//    }
//
//
//    @DeleteMapping("/buys/{buyId}/{familyId}")
//    @Operation(summary = "Удаляет покупку",
//            description = "Удаляет покупку по id",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    public  ResponseEntity<Void> deleteBuy(@PathVariable Integer buyId,@PathVariable Integer familyId){
//        service.deleteBuy(buyId,familyId);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @Operation(summary = "Баланс семьи",
//            description = "Возвращает сумму баланса по id семьи",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "Успешное выполнение"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    @GetMapping("/family/balance/{familyId}")
//    public ResponseEntity<Double> getBalance(@PathVariable Integer familyId) {
//        return ResponseEntity.status(200).body(service.getBalance(familyId));
//    }
//
//    @Operation(summary = "Пополнить баланс семьи",
//            description = "Пополняет балан семьи по id",
//            responses = {
//                    @ApiResponse(responseCode = "201",
//                            description = "Успешное выполнение, баланс пополнен"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    @PostMapping("/family/balance/{familyId}")
//    public ResponseEntity<Void> addMoney(@RequestBody MoneyDTO moneyDTO, @PathVariable Integer familyId){
//        service.addBalance(moneyDTO,familyId);
//        return ResponseEntity.status(201).build();
//    }
//    @Operation(summary = "Список семей",
//            description = "Получить список семей",
//            responses = {
//                    @ApiResponse(responseCode = "201",
//                            description = "Успешное выполнение, баланс пополнен"),
//                    @ApiResponse(responseCode = "401",
//                            description = "Требуется аутентификация"),
//                    @ApiResponse(responseCode = "403",
//                            description = "Аутентификация предоставлена, но у пользователя нет доступа"),
//                    @ApiResponse(responseCode = "404",
//                            description = "Ресурс не найден")
//            })
//    @GetMapping("/family")
//    public  ResponseEntity<List<FamilyDTO>> getFamilies(){
//        return  ResponseEntity.ok().body(service.getFamilies());
//    }
//}