package com.jayesh.bookmyshow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//RequiredArgsConstructor
@Entity
@Table(name = "seats")

public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private String seatType;

    public Seat(String seatNumber, String seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToMany(mappedBy = "seat",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ShowSeat> showSeats;

    //helper
    public void addShowSeat(ShowSeat showSeat){
        this.showSeats.add(showSeat);
        showSeat.setSeat(this);
    }
    public void removeShowSeat(ShowSeat showSeat){
        this.showSeats.remove(showSeat);
        showSeat.setSeat(null);
    }
}
