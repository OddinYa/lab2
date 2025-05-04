package com.example.lab2.models.dto;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import com.example.lab2.config.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BuyDTO
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")


public class BuyDTO   {

  @JsonProperty("id")

  private Integer id = null;

  @JsonProperty("idUser")
  private Integer idUser = null;
  @JsonProperty("cost")

  private Float cost = null;

  @JsonProperty("name")

  private String name = null;

  @JsonProperty("date")

  private LocalDate date = null;


  @JsonProperty("userName")
  private String userName;

  public BuyDTO cost(Float cost) { 

    this.cost = cost;
    return this;
  }
  /**
   * Id товара
   * @return id
   **/
    @Schema(required = true,description = "Id товара")
    @NotNull
    public Integer getId(){return id;}

    public void setId(Integer id){
      this.id = id;
    }

  /**
   * Id покупателя
   * @return idUser
   **/
  @Schema(required = true,description = "Id покупателя")
  @NotNull
  public Integer getIdUser(){return idUser;}

  public void setIdUser(Integer idUser){
    this.idUser = idUser;
  }
  /**
   * Цена покупки
   * @return cost
   **/
  
  @Schema(required = true, description = "Цена покупки")
  
  @NotNull
  public Float getCost() {  
    return cost;
  }

  /**
   * Имя пользователя
   * @return userName
   **/
  @Schema(required = true,description = "Имя пользователя")
  @NotNull
  public  String getUserName(){
    return userName;
  }
  public void setUserName(String str){
    this.userName = str;
  }


  public void setCost(Float cost) { 

    this.cost = cost;
  }

  public BuyDTO name(String name) { 

    this.name = name;
    return this;
  }

  /**
   * Наименование покупки
   * @return name
   **/
  
  @Schema(required = true, description = "Наименование покупки")
  
  @NotNull
  public String getName() {  
    return name;
  }



  public void setName(String name) { 

    this.name = name;
  }

  public BuyDTO date(LocalDate date) { 

    this.date = date;
    return this;
  }

  /**
   * Дата покупки
   * @return date
   **/
  
  @Schema(required = true, description = "Дата покупки")
  
@Valid
  @NotNull
  public LocalDate getDate() {  
    return date;
  }



  public void setDate(LocalDate date) { 

    this.date = date;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BuyDTO buyDTO = (BuyDTO) o;
    return Objects.equals(this.idUser,buyDTO.idUser) &&
            Objects.equals(this.cost, buyDTO.cost) &&
            Objects.equals(this.name, buyDTO.name) &&
            Objects.equals(this.id,buyDTO.id)&&
            Objects.equals(this.date, buyDTO.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id,idUser,cost, name, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BuyDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append(("\n"));
    sb.append("    idUser: ").append(toIndentedString(idUser)).append(("\n"));
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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
