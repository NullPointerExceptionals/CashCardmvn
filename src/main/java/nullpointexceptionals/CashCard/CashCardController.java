package nullpointexceptionals.CashCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;



import main.java.nullpointexceptionals.CashCard;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/cashcards")
public class CashCardController {
   private CashCardRepository cashCardRepository;

   public CashCardController(CashCardRepository cashCardRepository) {
      this.cashCardRepository = cashCardRepository;
   }


   private CashCard findCashCard(Long requestedId) {
      return cashCardRepository.findById(requestedId).orElse(null);
   }

   // Test mapping to get by requested ID only
   @GetMapping("/{requestedId}")
   public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
      CashCard cashCard = findCashCard(requestedId);
      if (cashCard != null) {
         return ResponseEntity.ok(cashCard);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Find all cards by owner
   @GetMapping("/owner/{owner}")
   public ResponseEntity<List<CashCard>> getCashCardsByOwner(@PathVariable String owner) {
      List<CashCard> cashCards = cashCardRepository.findByOwner(owner);
      if (cashCards.isEmpty()) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(cashCards);
   }

   // Find by Owner and ID
   @GetMapping("/owner/{owner}/{id}")
   public ResponseEntity<CashCard> findByOwnerAndId(@PathVariable String owner, @PathVariable Long id) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         return ResponseEntity.ok(cashCard);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   // Delete by Owner and ID
   @DeleteMapping("/owner/{owner}/{id}")
   public ResponseEntity<?> deleteById(@PathVariable String owner, @PathVariable Long id) {
      CashCard cashCard = cashCardRepository.findByOwnerAndId(owner, id);
      if (cashCard != null) {
         cashCardRepository.delete(cashCard);
         return ResponseEntity.noContent().build();
      } else {
         return ResponseEntity.notFound().build();
      }
   }

}
