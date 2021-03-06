/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import model.exceptions.DomainException;

/**
 *
 * @author LuanV
 */
public class Reservations {

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservations(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
        if (!checkout.after(checkin)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    /**
     * @return the roomNumber
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the checkin
     */
    public Date getCheckin() {
        return checkin;
    }

    /**
     * @return the checkout
     */
    public Date getCheckout() {
        return checkout;
    }

    public long duration() {
        long diff = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updatedDates(Date checkin, Date checkout) throws DomainException{
        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)) {
            throw new DomainException("Reservations dates for update must be future dates");
        }
        if (!checkout.after(checkin)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkin)
                + ", check-out: "
                + sdf.format(checkout)
                + ", "
                + duration()
                + " nights";

    }
}
