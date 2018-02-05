package com.jparest.service;

import com.jparest.model.Items;
import com.jparest.rest.patch.Patch;

import java.util.List;

public interface ItemService {

    List<Items> getAllItems();

    Items addItem(Items items);

    Items getItemById(Long itemId);

    void removeItemById(Long itemId);

    Items updateItemById(Long itemId, Items items);

    Items patchItemById(Long itemId, Patch patch);

    List<Items> getItemsByOrderId(Long orderId);
}
