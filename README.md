# Event Ticket Booking System

A Java console application for booking event tickets (movies and sports), built using core data structures Singly Linked List, Queue, and Stack to manage customers, ticket requests, and bookings.

## Overview

This project simulates a ticket reservation system where customers can register, browse available movie or sport events, book tickets, and cancel bookings. The system was built as a group project to apply and compare the behavior of different data structures in a real-world scenario.

## Data Structures & Design Rationale

| Structure | Used For | Why |
|---|---|---|
| **Singly Linked List** | Customer management | Dynamic memory allocation handles a varying number of customers efficiently without wasted space |
| **Queue (FIFO)** | Ticket request management | Processes ticket requests in the order they arrive — fair, first-come-first-served handling |
| **Stack (LIFO)** | Booking management | Enables quick access to and reversal of the most recent bookings (e.g., cancellations) |

## Features

- Add, search, and remove customers
- Browse available Movie and Sport events
- Reserve tickets with seat selection and automatic price calculation
- Cancel a booking or an individual ticket
- Track booking history per customer
- View the most frequently booked event
- Modify or remove customer records

## My Contribution

This was a 5-member team project. I implemented:
- **`showCustomers()`** (Singly Linked List) — traverses the customer list and displays all registered customers
- **`countTicketsByEvent()`** (Queue) — iterates through the ticket queue to count how many tickets are associated with a specific event, without altering the queue's order

## How to Run

```bash
javac Main.java
java Main
```

Follow the on-screen menu to add customers, book tickets, or view event statistics.
