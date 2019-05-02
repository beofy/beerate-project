package cn.beerate.controller;

import cn.beerate.service.ItemLoanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/itemloan")
public class ItemLoanController {

    private ItemLoanService itemLoanService;

    public ItemLoanController(ItemLoanService itemLoanService) {
        this.itemLoanService = itemLoanService;
    }


}
