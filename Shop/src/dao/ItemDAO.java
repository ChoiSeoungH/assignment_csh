package dao;

import Utils.InputManger;
import vo.Item;

import java.util.ArrayList;

public class ItemDAO {
  private final ArrayList<Item> itemList;

  public ItemDAO() {
    itemList = new ArrayList<>();
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }

  public void addItemFromData(String itemData) {
    String[] temp = itemData.split("\n");
    for (String s : temp) {
      String[] info = s.split("/");
      itemList.add(new Item(info[0], Integer.parseInt(info[1]), info[2]));
    }
  }

  public String getDataFromList() {
    String data = "";
    for (Item i : itemList) {
        data+= i.getName()+"/"+i.getPrice()+"/"+i.getCategory()+"\n" ;
    }
    return data;
  }

  public void printItem() {
    for (Item i : itemList) {
      System.out.println(i);
    }
  }

  public void buyItem(String log, UserDAO udao) {
    printItem();
    String item = InputManger.getValue("구매할 상품 >> ");
    if (hasItem(item)==-1) {
      System.out.println("상품명을 확인해주세요");
      return;
    }
    udao.addItemtoMyCart(log,item);
    System.out.println("구매완료");

  }

  private int hasItem(String item) {
    int idx=0;
    for (Item i : itemList) {
      if (i.getName().equals(item)) {
        return idx;
      }
      idx+=1;
    }
    return -1;
  }

  public void categoryManagement(UserDAO udao) {
  }

  public void addItem() {
    String item = InputManger.getValue("추가할 상품 >> ");
    if (hasItem(item)!=-1) {
      System.out.println("이미 존재하는 상품입니다.");
      return;
    }
    String price = InputManger.getValue("가격 >> ");
    String category = InputManger.getValue("카테고리 >> ");
    itemList.add(new Item(item, Integer.parseInt(price), category));

  }

  public void deleteItem(UserDAO udao) {
    String item = InputManger.getValue("삭제할 상품 >> ");
    int delIdx=hasItem(item);
    if (delIdx==-1) {
      System.out.println("존재하지 않는 상품입니다.");
      return;
    }
    udao.deleteAllCartItem(itemList.get(delIdx).getName());
    itemList.remove(delIdx);
    printItem();
    System.out.println("삭제완료");
  }
}
