package com.jparest.service.impl;

import com.jparest.exception.ItemNotFoundException;
import com.jparest.exception.OrderNotFoundException;
import com.jparest.exception.PatchFieldNotMatchException;
import com.jparest.exception.PatchOperationNotSupported;
import com.jparest.model.Items;
import com.jparest.model.Orders;
import com.jparest.repository.ItemRepository;
import com.jparest.repository.OrderRepository;
import com.jparest.rest.patch.Patch;
import com.jparest.rest.patch.PatchEnum;
import com.jparest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Items> getAllItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public Items addItem(Items items) {
        return this.itemRepository.save(items);
    }

    @Override
    public Items getItemById(Long itemId) throws ItemNotFoundException {
        Items items = this.itemRepository.findOne(itemId);
        if (items == null) {
            throw new ItemNotFoundException("Item not found");
        }
        return items;
    }

    @Override
    public void removeItemById(Long itemId) throws ItemNotFoundException {
        if (!this.itemRepository.exists(itemId)) {
            throw new ItemNotFoundException("Item not found");
        }
        this.itemRepository.delete(itemId);
    }

    @Override
    public Items updateItemById(Long itemId, Items items) throws ItemNotFoundException {
        Items item = this.itemRepository.findOne(itemId);
        if (item == null) {
            throw new ItemNotFoundException("Item not found");
        }
        // Update.
        item.setCode(items.getCode());
        item.setName(items.getName());
        item.setDescription(items.getDescription());
        return this.itemRepository.save(item);
    }

    @Override
    public Items patchItemById(Long itemId, Patch patch) throws ItemNotFoundException,
            PatchOperationNotSupported, PatchFieldNotMatchException {
        // Check patch operation.
        if (patch.getPatchEnum().equals(PatchEnum.REPLACE)) {
            // Get item.
            Items items = this.itemRepository.findOne(itemId);
            if (items == null) {
                throw new ItemNotFoundException("Item not found");
            }
            // Check what field to patch.
            if (patch.getField().equals(Items.ITEM_CODE)) {
                items.setCode(patch.getValue());
            } else if (patch.getField().equals(Items.ITEM_NAME)) {
                items.setName(patch.getValue());
            } else if (patch.getField().equals(Items.ITEM_DESCRIPTION)) {
                items.setDescription(patch.getValue());
            } else {
                throw new PatchFieldNotMatchException("Patch field " + patch.getField() + " not match");
            }
            // Update items.
            return this.itemRepository.save(items);
        } else {
            throw new PatchOperationNotSupported("Patch operation not supported");
        }
    }

    @Override
    public List<Items> getItemsByOrderId(Long orderId) throws OrderNotFoundException {
        Orders orders = this.orderRepository.findOne(orderId);
        if (orders == null) {
            throw new OrderNotFoundException("Order not found");
        }
        return this.itemRepository.findAllByOrders(orders);
    }
}
