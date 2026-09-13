package com.library.management.controller;

import com.library.management.model.*; import com.library.management.service.LibraryService; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.util.List;

@RestController @RequestMapping("/api") public class LibraryController{
 private final LibraryService service; public LibraryController(LibraryService service){this.service=service;}
 @GetMapping("/books") public List<Book> books(){return service.books();}
 @PostMapping("/admin/books") @ResponseStatus(HttpStatus.CREATED) public Book addBook(@RequestBody Book b){return service.addBook(b);}
 @GetMapping("/admin/members") public List<Member> members(){return service.members();}
 @PostMapping("/admin/members") @ResponseStatus(HttpStatus.CREATED) public Member addMember(@RequestBody Member m){return service.addMember(m);}
 @PostMapping("/member/borrow/{bookId}/{memberId}") public BorrowTransaction borrow(@PathVariable Long bookId,@PathVariable Long memberId){return service.borrow(bookId,memberId);}
 @PostMapping("/member/return/{transactionId}") public BorrowTransaction returnBook(@PathVariable Long transactionId){return service.returnBook(transactionId);}
 @GetMapping("/member/transactions") public List<BorrowTransaction> transactions(){return service.transactions();}
}
