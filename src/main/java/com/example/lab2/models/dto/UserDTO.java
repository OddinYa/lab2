package com.example.lab2.models.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.example.lab2.models.dto.BuyDTO;
import com.example.lab2.models.dto.MoneyDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import com.example.lab2.config.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserDTO
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")


public class UserDTO   {

  @JsonProperty("id")
  @NotNull
  private Integer id;


  @JsonProperty("money")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private MoneyDTO money = null;

  @JsonProperty("spending")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Float spending = null;

  @JsonProperty("name")

  private String name = null;

  @JsonProperty("buyList")
  @Valid
  private List<BuyDTO> buyList = null;


  public UserDTO money(MoneyDTO money) { 

    this.money = money;
    return this;
  }
  /**
   * id пользователя
   * @return id
   **/
  public Integer getId(){ return id;}

  public void setId(Integer id){this.id=id;}
  /**
   * Деньги пользователя
   * @return money
   **/
  
  @Schema(description = "")
  
@Valid
  public MoneyDTO getMoney() {  
    return money;
  }



  public void setMoney(MoneyDTO money) { 
    this.money = money;
  }

  public UserDTO spending(Float spending) { 

    this.spending = spending;
    return this;
  }

  /**
   * Сумма потраченных средств
   * @return spending
   **/
  
  @Schema(description = "Сумма потраченных средств")
  
  public Float getSpending() {  
    return spending;
  }



  public void setSpending(Float spending) { 
    this.spending = spending;
  }

  public UserDTO name(String name) { 

    this.name = name;
    return this;
  }

  /**
   * Имя пользователя
   * @return name
   **/
  
  @Schema(required = true, description = "Имя пользователя")
  
  @NotNull
  public String getName() {  
    return name;
  }



  public void setName(String name) { 

    this.name = name;
  }

  public UserDTO buyList(List<BuyDTO> buyList) { 

    this.buyList = buyList;
    return this;
  }

  public UserDTO addBuyListItem(BuyDTO buyListItem) {
    if (this.buyList == null) {
      this.buyList = new ArrayList<BuyDTO>();
    }
    this.buyList.add(buyListItem);
    return this;
  }

  /**
   * Список покупок пользователя
   * @return buyList
   **/
  
  @Schema(description = "Список покупок пользователя")
  @Valid
  public List<BuyDTO> getBuyList() {  
    return buyList;
  }



  public void setBuyList(List<BuyDTO> buyList) { 
    this.buyList = buyList;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDTO userDTO = (UserDTO) o;
    return Objects.equals(this.money, userDTO.money) &&
        Objects.equals(this.spending, userDTO.spending) &&
        Objects.equals(this.name, userDTO.name) &&
        Objects.equals(this.buyList, userDTO.buyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(money, spending, name, buyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDTO {\n");
    
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    spending: ").append(toIndentedString(spending)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    buyList: ").append(toIndentedString(buyList)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
