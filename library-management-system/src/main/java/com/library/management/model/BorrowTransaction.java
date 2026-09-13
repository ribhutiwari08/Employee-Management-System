package com.library.management.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="borrow_transactions")
public class BorrowTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="book_id") private Book book;
 @ManyToOne(optional=false) @JoinColumn(name="member_id") private Member member;
 @Column(nullable=false) private LocalDate borrowedAt;
 private LocalDate dueDate;
 private LocalDate returnedAt;
 public BorrowTransaction() {}
 public Long getId(){return id;} public Book getBook(){return book;} public void setBook(Book v){book=v;}
 public Member getMember(){return member;} public void setMember(Member v){member=v;} public LocalDate getBorrowedAt(){return borrowedAt;} public void setBorrowedAt(LocalDate v){borrowedAt=v;}
 public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;} public LocalDate getReturnedAt(){return returnedAt;} public void setReturnedAt(LocalDate v){returnedAt=v;}
 public boolean isOverdue(){return returnedAt==null && dueDate!=null && LocalDate.now().isAfter(dueDate);}
 public long getOverdueDays(){if(!isOverdue()) return 0; return java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnedAt==null?LocalDate.now():returnedAt);}
}
