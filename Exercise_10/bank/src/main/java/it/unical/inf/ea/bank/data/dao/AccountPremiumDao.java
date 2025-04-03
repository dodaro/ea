package it.unical.inf.ea.bank.data.dao;

import it.unical.inf.ea.bank.data.entities.AccountPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountPremiumDao extends JpaRepository<AccountPremium, Long> {

  List<AccountPremium> findAllByPromotion_And_RegionCode_And_Points_GreaterThan_OrderByCredentialUsername(
      AccountPremium.Promotion promotion,
      Integer regionCode,
      Integer point);
}
