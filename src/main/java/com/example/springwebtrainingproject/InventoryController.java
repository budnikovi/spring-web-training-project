package com.example.springwebtrainingproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InventoryController {
    List<Inventory> inventoryList = new ArrayList<>();

    @GetMapping("form")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        Inventory inventory = getNewInventory(id);
        model.addAttribute("inventory", inventory);
        return "form";
    }

    @GetMapping("inventory")
    public String getInventory(Model model) {
        if (!model.asMap().isEmpty()) {
            model.addAttribute("message", model.asMap().get("message"));
        }
        model.addAttribute("inventoryList", inventoryList);
        return "inventory";
    }

    @PostMapping("/submitForm")
    public String submitForm(Inventory inventory, RedirectAttributes redirectAttributes) {
        int index = getInventoryIndex(inventory.getId());
        if (index != -1) {
            inventoryList.remove(index);
            inventoryList.add(inventory);
        } else {
            inventoryList.add(inventory);
            redirectAttributes.addFlashAttribute("message", "You successfully submitted the item!");
        }
        return "redirect:/inventory";
    }

    private Inventory getNewInventory(String id) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getId().equals(id)) {
                return inventory;
            }
        }
        return new Inventory();
    }

    private int getInventoryIndex(String id) {
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
