package com.example.lab2.service;

import com.example.lab2.models.*;
import com.example.lab2.models.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements FinanceService {

    private static final List<Family> listFamily = new ArrayList<>();

    @Override
    public void addUser(UserDTO userDTO, Integer familyId) {
        Family family = findFamilyById(familyId);

        User user = convertToUser(userDTO);
        user.setId(getNextUserId());
        family.getUserList().add(user);
    }

    @Override
    public void createNewFamily(UserDTO userDTO) {
        User user = convertToUser(userDTO);
        user.setId(getNextUserId());

        Money familyMoney = new Money(0.0f);
        familyMoney.setId(getNextMoneyId());

        Family family = new Family(familyMoney);
        family.setId(getNextFamilyId());
        family.getUserList().add(user);

        listFamily.add(family);
    }

    @Override
    public UserDTO getUser(Integer userId) {
        User user = findUserById(userId);
        return convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        listFamily.forEach(family ->
                family.getUserList().removeIf(user -> user.getId().equals(userId))
        );
    }

    @Override
    public void updateUser(Integer userId) {
        // Реализация требует дополнительных данных
    }

    @Override
    public void addBuy(BuyDTO buyDTO, Integer familyId) {
        Family family = findFamilyById(familyId);
        User user = family.getUserList().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No users in family"));

        Buy buy = convertToBuy(buyDTO);
        buy.setId(getNextBuyId());

        user.getBuyList().add(buy);
        user.setSpending(user.getSpending() + buy.getCost());
        user.getMoney().setCash(user.getMoney().getCash() - buy.getCost());
    }

    @Override
    public List<BuyDTO> getBuys(Integer familyId) {
        return findFamilyById(familyId).getUserList().stream()
                .flatMap(user -> user.getBuyList().stream())
                .map(this::convertToBuyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBuy(Integer buyId, Integer familyId) {
        Family family = findFamilyById(familyId);
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
        return (double) findFamilyById(familyId).getMoney().getCash();
    }

    @Override
    public void addBalance(MoneyDTO moneyDTO, Integer familyId) {
        Family family = findFamilyById(familyId);
        family.getMoney().setCash(
                family.getMoney().getCash() + moneyDTO.getCash()
        );
    }

    @Override
    public List<FamilyDTO> getFamilies() {
        return listFamily.stream()
                .map(this::convertToFamilyDTO)
                .collect(Collectors.toList());
    }

    // Вспомогательные методы
    private Family findFamilyById(Integer familyId) {
        return listFamily.stream()
                .filter(f -> f.getId().equals(familyId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Family not found"));
    }

    private User findUserById(Integer userId) {
        return listFamily.stream()
                .flatMap(f -> f.getUserList().stream())
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private int getNextFamilyId() {
        return listFamily.stream()
                .mapToInt(Family::getId)
                .max().orElse(0) + 1;
    }

    private int getNextUserId() {
        return listFamily.stream()
                .flatMap(f -> f.getUserList().stream())
                .mapToInt(User::getId)
                .max().orElse(0) + 1;
    }

    private int getNextMoneyId() {
        int maxFamilyMoney = listFamily.stream()
                .mapToInt(f -> f.getMoney().getId())
                .max().orElse(0);
        int maxUserMoney = listFamily.stream()
                .flatMap(f -> f.getUserList().stream())
                .mapToInt(u -> u.getMoney().getId())
                .max().orElse(0);
        return Math.max(maxFamilyMoney, maxUserMoney) + 1;
    }

    private int getNextBuyId() {
        return listFamily.stream()
                .flatMap(f -> f.getUserList().stream())
                .flatMap(u -> u.getBuyList().stream())
                .mapToInt(Buy::getId)
                .max().orElse(0) + 1;
    }

    // Конвертеры DTO
    private User convertToUser(UserDTO dto) {
        Money money = new Money(dto.getMoney().getCash());
        money.setId(getNextMoneyId());

        User user = new User(money, dto.getName());
        user.setSpending(dto.getSpending());
        user.setBuyList(new ArrayList<>());
        return user;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setMoney(new MoneyDTO().cash(user.getMoney().getCash()));
        dto.setName(user.getName());
        dto.setSpending(user.getSpending());
        dto.setBuyList(user.getBuyList().stream()
                .map(this::convertToBuyDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    private Buy convertToBuy(BuyDTO dto) {
        return new Buy(dto.getCost(),dto.getName(),dto.getDate());
    }

    private BuyDTO convertToBuyDTO(Buy buy) {
        BuyDTO dto = new BuyDTO();
        dto.setCost(buy.getCost());
        dto.setName(buy.getName());
        dto.setDate(buy.getDate());
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
}