package com.example.lab2.service;

import com.example.lab2.models.*;
import com.example.lab2.models.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements FinanceService {

    private static final Family familyObj = initFamili();

    @Override
    public void addUser(UserDTO userDTO, Integer familyId) {
        Family family = familyObj;

        User user = convertToUser(userDTO);
        user.setId(getNextUserId());
        family.getUserList().add(user);
    }


    @Override
    public UserDTO getUser(Integer userId) {
        User user = findUserById(userId);
        return convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Integer userId) {

        familyObj.getUserList().removeIf(user -> user.getId().equals(userId));
    }


    @Override
    public void addBuy(BuyDTO buyDTO, Integer familyId) {
        Family family =familyObj;
        User user = family.getUserList().stream()
                .filter(u -> Objects.equals(u.getId(), buyDTO.getIdUser()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User with id " +  buyDTO.getIdUser()  + " not found"));

        Buy buy = convertToBuy(buyDTO);
        buy.setId(getNextBuyId());
        buy.setUserName(user.getName());
        user.getBuyList().add(buy);
        user.setSpending(user.getSpending() + buy.getCost());
        user.getMoney().setCash(user.getMoney().getCash() - buy.getCost());
    }

    @Override
    public List<BuyDTO> getBuys(Integer familyId) {
        return familyObj.getUserList().stream()
                .flatMap(user -> user.getBuyList().stream())
                .map(this::convertToBuyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBuy(Integer buyId, Integer familyId) {
        Family family = familyObj;
        family.getUserList().forEach(user -> {
            Optional<Buy> buy = user.getBuyList().stream()
                    .filter(b -> b.getId().equals(buyId))
                    .findFirst();
            if (buy.isPresent()) {
                user.getBuyList().remove(buy.get());
                user.setSpending(user.getSpending() - buy.get().getCost());
                user.getMoney().setCash(user.getMoney().getCash() + buy.get().getCost());
            }
        });
    }

    @Override
    public Double getBalance(Integer familyId) {
        return (double) familyObj.getMoney().getCash();
    }

    @Override
    public void addBalance(MoneyDTO moneyDTO, Integer familyId) {
        Family family = familyObj;
        family.getMoney().setCash(
                family.getMoney().getCash() + moneyDTO.getCash()
        );
    }

    @Override
    public List<UserDTO> getUsers() {
        Family family = familyObj;

        return family.getUserList().
                stream().
                map(this::convertToUserDTO).
                collect(Collectors.toList());

    }

    // @Override
   // public List<FamilyDTO> getFamilies() {
   //     return listFamily.stream()
   //             .map(this::convertToFamilyDTO)
   //             .collect(Collectors.toList());
   // }

    // Вспомогательные методы
  // private Family findFamilyById(Integer familyId) {
  //     return listFamily.stream()
  //             .filter(f -> f.getId().equals(familyId))
  //             .findFirst()
  //             .orElseThrow(() -> new RuntimeException("Family not found"));
  // }

  // private User findUserById(Integer userId) {
  //     return listFamily.stream()
  //             .flatMap(f -> f.getUserList().stream())
  //             .filter(u -> u.getId().equals(userId))
  //             .findFirst()
  //             .orElseThrow(() -> new RuntimeException("User not found"));
  // }

    private User findUserById(Integer userId){
        return familyObj.getUserList().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
    }
  //  private int getNextFamilyId() {
  //      return listFamily.stream()
  //              .mapToInt(Family::getId)
  //              .max().orElse(0) + 1;
  //  }

   // private int getNextUserId() {
   //     return listFamily.stream()
   //             .flatMap(f -> f.getUserList().stream())
   //             .mapToInt(User::getId)
   //             .max().orElse(0) + 1;
   // }

    private int getNextUserId() {
        return familyObj.getUserList().stream()
                .mapToInt(User::getId)
                .max().orElse(0) + 1;
    }
   // private int getNextMoneyId() {
   //     int maxFamilyMoney = listFamily.stream()
   //             .mapToInt(f -> f.getMoney().getId())
   //             .max().orElse(0);
   //     int maxUserMoney = listFamily.stream()
   //             .flatMap(f -> f.getUserList().stream())
   //             .mapToInt(u -> u.getMoney().getId())
   //             .max().orElse(0);
   //     return Math.max(maxFamilyMoney, maxUserMoney) + 1;
   // }

  // private int getNextBuyId() {
  //     return listFamily.stream()
  //             .flatMap(f -> f.getUserList().stream())
  //             .flatMap(u -> u.getBuyList().stream())
  //             .mapToInt(Buy::getId)
  //             .max().orElse(0) + 1;
  // }

        private int getNextBuyId() {
            return familyObj.getUserList().stream()
                    .flatMap(u -> u.getBuyList().stream())
                    .mapToInt(Buy::getId)
                    .max().orElse(0) + 1;
        }

    // Конвертеры DTO
    private User convertToUser(UserDTO dto) {
        Money money = new Money(dto.getMoney().getCash());
       // money.setId(getNextMoneyId());

        User user = new User(money, dto.getName());
        user.setId(getNextUserId());
        user.setSpending(dto.getSpending());
        user.setBuyList(new ArrayList<>());
        return user;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setMoney(new MoneyDTO().cash(user.getMoney().getCash()));
        dto.setName(user.getName());
        dto.setSpending(user.getSpending());
        dto.setBuyList(user.getBuyList().stream()
                .map(this::convertToBuyDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private Buy convertToBuy(BuyDTO dto) {
        return new Buy(dto.getId(),dto.getIdUser(),dto.getCost(),dto.getName(),dto.getDate(),dto.getUserName());
    }

    private BuyDTO convertToBuyDTO(Buy buy) {
        BuyDTO dto = new BuyDTO();
        dto.setId(buy.getId());
        dto.setCost(buy.getCost());
        dto.setName(buy.getName());
        dto.setDate(buy.getDate());
        dto.setUserName(buy.getUserName());
        return dto;
    }

    private FamilyDTO convertToFamilyDTO(Family family) {
        FamilyDTO dto = new FamilyDTO();
        dto.setMoney(new MoneyDTO().cash(family.getMoney().getCash()));
        dto.setUserList(family.getUserList().stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    //TODO: Удалить как подлючу к бд
    private static Family initFamili(){
        Money money = new Money(467);
        User user = new User(money,"Сергей");
        user.setId(1);
        Family family = new Family(money);
        family.getUserList().add(user);

        return family;
    }
}