package com.apiRest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	
    private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String login;
	private String senha; 
	private String nome;
	
	@OneToMany(mappedBy ="usuario" ,orphanRemoval = true, cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Telefone> telefone = new ArrayList<Telefone>();
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "usuario_id", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id"},
           name="unique_role_user"),
	joinColumns =@JoinColumn(name="usuario_id",
	   referencedColumnName = "id",
	    table ="usuario", unique = false,
	     foreignKey = @ForeignKey(value=ConstraintMode.CONSTRAINT,name ="usuario_fk")),
	   inverseJoinColumns =@JoinColumn(name="role_id", unique = false,
	              referencedColumnName = "id", updatable = false,
	                table = "role" , foreignKey = @ForeignKey(value =ConstraintMode.CONSTRAINT,name="role_fk")))
	private List<Role> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Telefone> getTelefone() {
		return telefone;
	}
	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/*S??o os acessos do usu??rio ROLE_ADMIN OU ROLE_VISITANTE*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.roles;
	}
	@Override
	public String getPassword() {
		
		return this.senha;
	}
	@Override
	public String getUsername() {
		
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}
	@Override
	public boolean isEnabled() {

		return true;
	}
	
	
  
	
}
