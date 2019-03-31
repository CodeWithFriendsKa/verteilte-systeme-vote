package de.dhbw.vote.voting.jpa;

import de.dhbw.vote.voting.ejb.DateExtensions;
import de.dhbw.vote.common.jpa.Voter;
import java.awt.Image;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Entity
@Table(name= "UP_DOWN_VOTE")
public class UpDownVote implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATOR")
    @ManyToOne
    private Voter creator;
    @OneToMany
    private List<Voter> upVotes;
    @Column(name = "UPSIZE")
    private int upSize;
    @OneToMany
    private List<Voter> downVotes;
    @Column(name = "DOWNSIZE")
    private int downSize;
    @Column(name = "IMAGE") 
    private Image image;
    @Column(name = "VOTEDATE")
    private Date date;
    @Column(name = "CATEGORY")
    private Category category;

    public UpDownVote(String description, Voter creator, List<Voter> upVotes, List<Voter> downVotes, Image image, Category category) {
        this.description = description;
        this.creator = creator;
        this.upVotes = upVotes;
        this.upSize = upVotes.size();
        this.downVotes = downVotes;
        this.downSize = downVotes.size();
        this.image = image;
        this.date = DateExtensions.now();
        this.category = category;
    }
    public UpDownVote(String description, Voter creator, List<Voter> upVotes, List<Voter> downVotes) {
        this.description = description;
        this.creator = creator;
        this.upVotes = upVotes;
        this.upSize = upVotes.size();
        this.downVotes = downVotes;
        this.downSize = downVotes.size();
        this.image = null;
        this.date = DateExtensions.now();
        this.category = Category.UNBEKANNT;
    }    
    public UpDownVote(String description, Voter creator, Category category) {
        this.description = description;
        this.creator = creator;
        this.upVotes = new ArrayList();
        this.upSize = 0;
        this.downVotes = new ArrayList();
        this.downSize = 0;
        this.image = null;
        this.date = DateExtensions.now();
        this.category = category;
    }

    public UpDownVote() {
        this.description = "";
        this.creator = new Voter();
        this.upVotes = new ArrayList<>();
        this.upSize = 0;
        this.downVotes = new ArrayList<>();
        this.downSize = 0;
        this.image = null;
        this.date = DateExtensions.now();
        this.category = Category.UNBEKANNT;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public Voter getCreator() {return creator;}
    public void setCreator(Voter creator) {this.creator = creator;}
    public List<Voter> getUpVotes() {return upVotes;}
    public void setUpVotes(List<Voter> upVotes) {this.upVotes = upVotes; this.upSize = this.upVotes.size();}
    public List<Voter> getDownVotes() {return downVotes;}
    public void setDownVotes(List<Voter> downVotes) {this.downVotes = downVotes; this.downSize = this.downVotes.size();}
    public Image getImage() {return image;}
    public void setImage(Image image) {this.image = image;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}
    public int getUpSize() {return upSize;}
    public void setUpSize(int upSize) {this.upSize = upSize;}
    public int getDownSize() {return downSize;}
    public void setDownSize(int downSize) {this.downSize = downSize;}

    @Override
    public String toString() {
        return "UpDownVote{" + "id=" + id + ", description=" + description + ", creator=" + creator + ", upVotes=" + upVotes + ", upSize=" + upSize + ", downVotes=" + downVotes + ", downSize=" + downSize + ", image=" + image + ", date=" + date + ", category=" + category + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.creator);
        hash = 97 * hash + Objects.hashCode(this.upVotes);
        hash = 97 * hash + this.upSize;
        hash = 97 * hash + Objects.hashCode(this.downVotes);
        hash = 97 * hash + this.downSize;
        hash = 97 * hash + Objects.hashCode(this.image);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UpDownVote other = (UpDownVote) obj;
        if (this.upSize != other.upSize) {
            return false;
        }
        if (this.downSize != other.downSize) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.creator, other.creator)) {
            return false;
        }
        if (!Objects.equals(this.upVotes, other.upVotes)) {
            return false;
        }
        if (!Objects.equals(this.downVotes, other.downVotes)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.category != other.category) {
            return false;
        }
        return true;
    }

    
}
