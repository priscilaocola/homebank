package com.mindhub.homebanking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;


        @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
        private Set<Account> accounts = new HashSet<>();

        @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
        private Set<ClientLoan> clientLoans = new HashSet<>();

        @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
        private Set<Card> cards = new HashSet<>();

        public Client() {

        }
        public Client(String firstName, String lastName, String email,String password) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.password= password;

        }

        public  String getFirstName() {
                return firstName;
        }
        public void setFirstName(String firstName) {
                this.firstName= firstName;
        }
        public String getLastName() {
                return lastName;
        }
        public void setLastName(String lastName) {
              this.lastName = lastName;
        }
        public String getEmail() {
                return email;
        }
        public void setEmail(String email) {
                this.email = email;
        }
        public Long getId() {
                return id;
        }
        public String getPassword() {return password;}
        public void setPassword(String password){
                this.password=password;
        }

        public Set<Account> getAccounts() {
                return accounts;
        }

        public void setAccounts(Set<Account> accounts) {
                this.accounts = accounts;
        }

        public void addAccount(Account account) {
                account.setClient(this);
                this.accounts.add(account);
        }
        public void addClientLoan(ClientLoan clientLoan) {
                clientLoan.setClient(this);
                clientLoans.add(clientLoan);
        }
        public  void addCard(Card card){
                card.setClient(this);
                this.cards.add(card);
        }
        public Set<Card> getCards() {return cards;}

        @JsonIgnore
        public Set<Loan> getLoans() {
                return clientLoans.stream().map(clientLoan -> clientLoan.getLoan()).collect(Collectors.toSet());
        }

        public Set<ClientLoan> getClientLoans() {
                return clientLoans;
        }

        public void setClientLoans(Set<ClientLoan> clientLoans) {
                this.clientLoans = clientLoans;
        }


        @Override
        public String toString() {return firstName + " " + lastName + " " + email + " " + id;
}



}
