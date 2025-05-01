package com.example.lab2.models.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.example.lab2.models.dto.MoneyDTO;
import com.example.lab2.models.dto.UserDTO;
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
 * FamilyDTO
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")


public class FamilyDTO   {
  @JsonProperty("money")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private MoneyDTO money = null;

  @JsonProperty("userList")
  @Valid
  private List<UserDTO> userList = null;

  public FamilyDTO money(MoneyDTO money) { 

    this.money = money;
    return this;
  }

  /**
   * Get money
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

  public FamilyDTO userList(List<UserDTO> userList) { 

    this.userList = userList;
    return this;
  }

  public FamilyDTO addUserListItem(UserDTO userListItem) {
    if (this.userList == null) {
      this.userList = new ArrayList<UserDTO>();
    }
    this.userList.add(userListItem);
    return this;
  }

  /**
   * Список пользователей в семье
   * @return userList
   **/
  
  @Schema(description = "Список пользователей в семье")
  @Valid
  public List<UserDTO> getUserList() {  
    return userList;
  }



  public void setUserList(List<UserDTO> userList) { 
    this.userList = userList;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FamilyDTO familyDTO = (FamilyDTO) o;
    return Objects.equals(this.money, familyDTO.money) &&
        Objects.equals(this.userList, familyDTO.userList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(money, userList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FamilyDTO {\n");
    
    sb.append("    money: ").append(toIndentedString(money)).append("\n");
    sb.append("    userList: ").append(toIndentedString(userList)).append("\n");
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
