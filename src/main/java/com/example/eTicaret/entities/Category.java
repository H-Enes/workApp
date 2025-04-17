package com.example.eTicaret.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "category")
@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	private String name;
	
    @ManyToMany(mappedBy = "categories") // Product içindeki alan adıyla aynı olmalı
    private Set<Product> products;
    
    
    /* Veritabanı Sorgusu: Productları getirmek için bir sorgu çalıştırdığınızda,
       Hibernate başlangıçta sadece Product nesnelerini yükler. categories kümesi lazy loading olarak
       işaretlendiğinden, bu aşamada henüz yüklenmez.

		Lazy Loading: ProductConverter içinde product.getCategories() çağrısı yaptığınızda,
		Hibernate ilgili Category nesnelerini veritabanından çeker ve categories kümesine ekler.

		Tekrarlanan Veri: Eğer aynı Product nesnesine birden fazla kez erişilirse (örneğin, bir döngü içinde)
		ve her erişimde getCategories() çağrılırsa, Hibernate her seferinde veritabanına gidip aynı
	    Category nesnelerini tekrar tekrar yükleyebilir. Bu, bellekte aynı id'ye sahip ama farklı referanslara
	    sahip birden fazla Category nesnesinin oluşmasına sebep olur.

		equals ve hashCode: İşte tam bu noktada equals ve hashCode metotları devreye girer. 
		Eğer bu metotlar doğru şekilde override edilmemişse (yani, sadece id alanını kullanarak değil),
		Java bu farklı referanslara sahip Category nesnelerini farklı olarak algılar. 
		Bu da ConcurrentModificationException hatasına veya Set gibi koleksiyonlarda beklenmedik davranışlara
		yol açabilir.

		Özetle: Aynı ID'ye sahip iki farklı Category nesnesi veritabanında değil, Java uygulamanızda,
		Hibernate'in lazy loading mekanizması ve equals/hashCode metotlarının doğru şekilde override
		edilmemesi sonucu oluşur. Bu nedenle, equals ve hashCode metotlarını id alanına göre override etmek,
		bu sorunu çözmek için kritik öneme sahiptir.*/
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); 
    }

}
