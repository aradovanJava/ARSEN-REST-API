package arsenwebdemo.arsenweb.enumeration;

public enum City {
  ZAGREB(10000), RIJEKA(51000), SPLIT(21000), OSIJEK(31000);

  private Integer postalCode;

  private City(Integer postalCode) {
    this.postalCode = postalCode;
  }

  public Integer getPostalCode() {
    return postalCode;
  }
}
