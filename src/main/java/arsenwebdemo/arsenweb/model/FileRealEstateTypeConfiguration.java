package arsenwebdemo.arsenweb.model;

import lombok.Getter;

@Getter
public enum FileRealEstateTypeConfiguration {

  HOUSE(6), APARTMENT(5);

  private final int numberOfLines;

  FileRealEstateTypeConfiguration(int numberOfLines) {
    this.numberOfLines = numberOfLines;
  }
}
