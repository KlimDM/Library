package com.fa.library.web;

import com.fa.library.model.Book;
import com.fa.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookDetailsController {
    private final BookRepository bookRepository;

    @GetMapping()
    public String showBookDetails(@RequestParam("id") Long id, @RequestParam("editMode")Boolean editMode, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElse(null));
        model.addAttribute("editMode", editMode);
        return "bookdetails";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    //TODO
    @GetMapping("/editBook")
    public String editBook(@RequestParam("id") Long id) {
        return "redirect:/book?id=%d&editMode=true".formatted(id);
    }

    @PostMapping("/issueBook")
    public String issueBook(@RequestParam("id") long id, @RequestParam("studentName") String studentName) {
        Book currentBook = bookRepository.findById(id).orElse(null);
        if (currentBook != null) {
            currentBook.setStudentName(studentName);
            currentBook.setIssueDate(LocalDate.now());
            bookRepository.save(currentBook);
        }
        bookRepository.findByKeyword("java");
        return "redirect:/book?id=%d&editMode=false".formatted(id);
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("id") long id) {
        Book currentBook = bookRepository.findById(id).orElse(null);
        if (currentBook != null) {
            currentBook.setStudentName(null);
            currentBook.setIssueDate(null);
            currentBook.setReturnDate(LocalDate.now());
            bookRepository.save(currentBook);
        }
        return "redirect:/book?id=%d&editMode=false".formatted(id);
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book")Book book) {
        if (book.getPublisher().equals("")) {
            book.setPublisher(null);
        }
        if (book.getThumbnailUrl().equals("")) {
            book.setThumbnailUrl(null);
        }
        bookRepository.save(book);
        return "redirect:/book?id=%d&editMode=false".formatted(book.getId());
    }
}
