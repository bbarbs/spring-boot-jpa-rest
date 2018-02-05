package com.jparest.web;

import com.jparest.mapper.ItemMapper;
import com.jparest.model.Items;
import com.jparest.model.dto.ItemDto;
import com.jparest.rest.ApiResponse;
import com.jparest.rest.patch.Patch;
import com.jparest.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/v1/api")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemMapper mapper;

    /**
     * Get item list.
     *
     * @return
     */
    @ApiOperation(
            value = "Get item list"
    )
    @GetMapping(
            value = "/items",
            produces = APPLICATION_JSON_VALUE
    )
    public List<ItemDto> getAllItems() {
        List<Items> items = this.itemService.getAllItems();
        return this.mapper.mapEntitiesToDTOs(items);
    }

    /**
     * Add new item
     *
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Add new item"
    )
    @PostMapping(
            value = "/items",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<ItemDto> addItem(@ApiParam(value = "Item details", required = true) @RequestBody ItemDto dto) {
        Items items = this.itemService.addItem(this.mapper.mapDTOtoEntity(dto));
        return new ApiResponse<>(HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.mapper.mapEntityToDTO(items)));
    }

    /**
     * Get item by Id.
     *
     * @param itemId
     * @return
     */
    @ApiOperation(
            value = "Get item by Id"
    )
    @GetMapping(
            value = "/items/{itemId}",
            produces = APPLICATION_JSON_VALUE
    )
    public ItemDto getItemById(@ApiParam(value = "Item Id", required = true) @PathVariable(name = "itemId") Long itemId) {
        Items items = this.itemService.getItemById(itemId);
        return this.mapper.mapEntityToDTO(items);
    }


    /**
     * Delete item by id.
     *
     * @param itemId
     * @return
     */
    @ApiOperation(
            value = "Delete item by Id"
    )
    @DeleteMapping(
            value = "/items/{itemId}",
            produces = TEXT_PLAIN_VALUE
    )
    public ResponseEntity deleteItemById(@ApiParam(value = "Item Id", required = true) @PathVariable(name = "itemId") Long itemId) {
        this.itemService.removeItemById(itemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update item details.
     *
     * @param itemId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Update item by Id"
    )
    @PutMapping(
            value = "/items/{itemId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<ItemDto> updateItemById(@ApiParam(value = "Item Id", required = true) @PathVariable(name = "itemId") Long itemId,
                                               @ApiParam(value = "Item details", required = true) @RequestBody ItemDto dto) {
        Items items = this.itemService.updateItemById(itemId, this.mapper.mapDTOtoEntity(dto));
        return new ApiResponse<>(HttpStatus.OK.value(),
                HttpStatus.OK,
                Arrays.asList(this.mapper.mapEntityToDTO(items)));
    }

    /**
     * Patch item details.
     *
     * @param itemId
     * @param patch
     * @return
     */
    @ApiOperation(
            value = "Patch item by Id"
    )
    @PatchMapping(
            value = "/items/{itemId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ApiResponse<ItemDto> patchItem(@ApiParam(value = "Item Id", required = true) @PathVariable(name = "itemId") Long itemId,
                                          @ApiParam(value = "Item patch details", required = true) @RequestBody Patch patch) {
        Items items = this.itemService.patchItemById(itemId, patch);
        return new ApiResponse<>(HttpStatus.OK.value(),
                HttpStatus.OK,
                Arrays.asList(this.mapper.mapEntityToDTO(items)));
    }
}
