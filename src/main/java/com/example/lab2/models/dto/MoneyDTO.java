package com.example.lab2.models.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * MoneyDTO
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2025-04-29T17:08:35.048350733Z[GMT]")


public class MoneyDTO   {
  @JsonProperty("cash")

  private Float cash = null;


  public MoneyDTO cash(Float cash) { 

    this.cash = cash;
    return this;
  }

  /**
   * Сумма денег
   * @return cash
   **/
  
  @Schema(required = true, description = "Сумма денег")
  
  @NotNull
  public Float getCash() {  
    return cash;
  }



  public void setCash(Float cash) { 

    this.cash = cash;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoneyDTO moneyDTO = (MoneyDTO) o;
    return Objects.equals(this.cash, moneyDTO.cash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoneyDTO {\n");
    
    sb.append("    cash: ").append(toIndentedString(cash)).append("\n");
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
