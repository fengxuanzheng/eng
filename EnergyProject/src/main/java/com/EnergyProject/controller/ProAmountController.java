package com.EnergyProject.controller;

import com.EnergyProject.server.ProAmountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProAmountController {

    @Autowired
    private ProAmountServer proAmountServer;
}
