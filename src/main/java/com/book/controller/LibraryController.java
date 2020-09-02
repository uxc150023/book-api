package com.book.controller;

import com.book.pojo.Book;
import com.book.result.JsonResult;
import com.book.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;

    @GetMapping("/book/findAll")
    public JsonResult<Object> list() throws Exception {
        return new JsonResult<>(bookService.list(), "0");
    }


    @PutMapping("/book/update")
    public JsonResult<Object> addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return new JsonResult<>(book, "0");
    }

    @PostMapping("/book/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }
    

    @GetMapping("/book/categories/{cid}")
    public JsonResult<Object> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return new JsonResult<>(bookService.listByCategory(cid), "0");
        } else {
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/book/search")
    public JsonResult<Object> searchResult(@RequestParam("keywords") String keywords) {
    	// 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return  new JsonResult<>(bookService.list(), "0");
        } else {
            return new JsonResult<>(bookService.search(keywords), "0");
        }
    }

}

