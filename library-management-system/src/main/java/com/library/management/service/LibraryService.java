package com.library.management.service;

import com.library.management.model.*;
import com.library.management.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {
 private final BookRepository books; private final MemberRepository members; private final BorrowTransactionRepository transactions;
 public LibraryService(BookRepository books,MemberRepository members,BorrowTransactionRepository transactions){this.books=books;this.members=members;this.transactions=transactions;}
 public List<Book> books(){return books.findAll();}
 public Book addBook(Book b){return books.save(b);}
 public List<Member> members(){return members.findAll();}
 public Member addMember(Member m){return members.save(m);}
 public BorrowTransaction borrow(Long bookId,Long memberId){
  Book b=books.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
  Member m=members.findById(memberId).orElseThrow(()->new RuntimeException("Member not found"));
  if(!b.isAvailable()) throw new IllegalStateException("Book is not available");
  b.setAvailable(false); books.save(b);
  BorrowTransaction t=new BorrowTransaction(); t.setBook(b); t.setMember(m); t.setBorrowedAt(LocalDate.now()); t.setDueDate(LocalDate.now().plusDays(14));
  return transactions.save(t);
 }
 public BorrowTransaction returnBook(Long transactionId){
  BorrowTransaction t=transactions.findById(transactionId).orElseThrow(()->new RuntimeException("Transaction not found"));
  if(t.getReturnedAt()!=null) throw new IllegalStateException("Book already returned");
  t.setReturnedAt(LocalDate.now()); t.getBook().setAvailable(true); books.save(t.getBook()); return transactions.save(t);
 }
 public List<BorrowTransaction> transactions(){return transactions.findAll();}
}
