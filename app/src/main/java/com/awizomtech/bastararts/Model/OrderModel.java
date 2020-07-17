package com.awizomtech.bastararts.Model;

public class OrderModel {
    public String UserID;
    public int OrderID;
    public int Quantity ;
    public String OrderNo;
    public String OrderDate;
    public float TotalAmount ;
    public Boolean IsPaymentDone;
    public String ShippingCharge ;
    public String Status ;
    public int AddressID ;
    public String AddressLineOne ;
    public String Landmark;
    public String PinCode ;
    public String Country ;
    public String State;
    public String City;
    public int ProductID ;
    public String ProductCode ;
    public String ProductName ;
    public String ProductImage ;
    public String Description;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public float getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        TotalAmount = totalAmount;
    }

    public Boolean getPaymentDone() {
        return IsPaymentDone;
    }

    public void setPaymentDone(Boolean paymentDone) {
        IsPaymentDone = paymentDone;
    }

    public String getShippingCharge() {
        return ShippingCharge;
    }

    public void setShippingCharge(String shippingCharge) {
        ShippingCharge = shippingCharge;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public String getAddressLineOne() {
        return AddressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        AddressLineOne = addressLineOne;
    }

    public String getLandmark() {
        return Landmark;
    }

    public void setLandmark(String landmark) {
        Landmark = landmark;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
