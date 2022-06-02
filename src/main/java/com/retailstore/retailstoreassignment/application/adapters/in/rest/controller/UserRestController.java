package com.retailstore.retailstoreassignment.application.adapters.in.rest.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1")
@Validated
public class UserRestController extends BaseRestController{


}
