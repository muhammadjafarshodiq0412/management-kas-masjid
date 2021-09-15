package com.managementkasmasjid.config;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.entity.*;
import com.managementkasmasjid.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DbInit implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    GlobalParamRepository globalParamRepository;
    @Autowired
    DanaRepository danaRepository;
    @Autowired
    CommonUserRepository commonUserRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    JournalRepository journalRepository;

    @Override
    public void run(String... args) throws Exception {
        userRoleInit();
        globalparamInit();
        danaInit();
        commonUserInit();
        transactionInit();
    }

    private void userRoleInit() {
        log.info("userRoleInit Started");
        List<Role> roles = roleRepository.findAll();
        if (roles.size() == 0) {
            List<Role> roleList = new ArrayList<Role>();
            roleList.add(new Role("ROLE_SUPER_USER", "Role Super User"));
            roleList.add(new Role("ROLE_ADMINISTRATOR", "Role Administrator"));
            roleList.add(new Role("ROLE_ADMIN", "Role Admin"));
            roleList.add(new Role("ROLE_SEKERTARIS", "Role Sekertaris"));
            roleRepository.saveAll(roleList);
        }

        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            Role roleSuperUser = roleRepository.findByCode("ROLE_SUPER_USER");
            Role roleAdministrator = roleRepository.findByCode("ROLE_ADMINISTRATOR");
            Role roleAdmin = roleRepository.findByCode("ROLE_ADMIN");
            Role roleSekertaris = roleRepository.findByCode("ROLE_SEKERTARIS");

            User userAdmin = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin123"),roleAdmin);
//            userAdmin.addRole(roleSuperUser);
//            userAdmin.addRole(roleAdministrator);
//            userAdmin.addRole(roleAdmin);
            userRepository.save(userAdmin);

            User userSekertaris = new User("sekertaris", "sekertaris@gmail.com", passwordEncoder.encode("sekertaris"),roleSekertaris);
//            userSekertaris.addRole(roleSekertaris);
            userRepository.save(userSekertaris);

        }
        log.info("userRoleInit Accomplish");
    }

    private void globalparamInit() {
        log.info("globalparamInit Started");
        List<GlobalParam> globalParams = globalParamRepository.findAll();
        if (globalParams.size() == 0) {
            GlobalParam paramDana1 = new GlobalParam(GlobalConstant.CATEGORY_DANA, "PENERIMAAN", "Penerimaan", "Category Dana");
            GlobalParam paramDana2 = new GlobalParam(GlobalConstant.CATEGORY_DANA, "PENGELUARAN", "Pengeluaran", "Category Dana");
            GlobalParam paramMusathik = new GlobalParam("CATEGORY_COMMON_USER", "MUSTAHIK", "Mustahik", "Category Common User");
            GlobalParam paramMuzakki = new GlobalParam("CATEGORY_COMMON_USER", "MUZAKKI", "Muzakki", "Category Common User");
            GlobalParam paramTransaction1 = new GlobalParam(GlobalConstant.CATEGORY_TRANSACTION, "PENERIMAAN", "Penerimaan", "Category Transaction");
            GlobalParam paramTransaction2 = new GlobalParam(GlobalConstant.CATEGORY_TRANSACTION, "PENGELUARAN", "Pengeluaran", "Category Transaction");
            GlobalParam paramJournal1 = new GlobalParam("CATEGORY_JOURNAL", "PENERIMAAN", "Penerimaan", "Category Journal");
            GlobalParam paramJournal2 = new GlobalParam("CATEGORY_JOURNAL", "PENGELUARAN", "Pengeluaran", "Category Journal");
            GlobalParam paramAccountKas = new GlobalParam("CATEGORY_ACCOUNT", "KAS", "Kas", "Account Kas");
            GlobalParam paramAccountPengeluaran = new GlobalParam("CATEGORY_ACCOUNT", "PENGELUARAN", "Pengeluaran", "Account Pengeluaran");
            GlobalParam paramAccountPenerimaan = new GlobalParam("CATEGORY_ACCOUNT", "PENERIMAAN", "Penerimaan", "Account Penerimaan");
            GlobalParam paramAccountBiaya = new GlobalParam("CATEGORY_ACCOUNT", "BIAYA", "Biaya", "Account Biaya");
            globalParamRepository.saveAll(Arrays.asList(paramDana1, paramDana2, paramMusathik, paramMuzakki, paramTransaction1, paramTransaction2, paramJournal1, paramJournal2,
                    paramAccountKas,paramAccountPengeluaran,paramAccountPenerimaan,paramAccountBiaya));
        }

        log.info("globalparamInit have been completed");
    }

    private void danaInit() {
        log.info("danaInit started");
        List<Dana> danas = danaRepository.findAll();
        GlobalParam catPenerimaan = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_DANA", "PENERIMAAN");
        GlobalParam catPengeluaran = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_DANA", "PENGELUARAN");
        if (danas.size() == 0) {
            Long cash = Long.valueOf(1000000);
            Dana danaPenerimaan = new Dana(cash, catPenerimaan);
            Dana danaPengeluaran = new Dana(cash, catPengeluaran);

            danaRepository.saveAll(Arrays.asList(danaPenerimaan, danaPengeluaran));
        }
        log.info("danaInit have been completed");
    }

    private void commonUserInit() {
        log.info("commonUserInit started");
        List<CommonUser> commonUsers = commonUserRepository.findAll();
        GlobalParam catMustahik = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_COMMON_USER", "MUSTAHIK");
        GlobalParam catMuzakki = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_COMMON_USER", "MUZAKKI");
        if (commonUsers.size() == 0) {
            CommonUser commonUser1 = new CommonUser("Dinda", "08988116753", "dinda0412@gmail.com", "Karawang", catMuzakki);
            CommonUser commonUser2 = new CommonUser("Asep","08988116753","asep@gmail.com","Karawang",catMuzakki);
            CommonUser commonUser3 = new CommonUser("Didin","08988116753","didin@gmail.com","Karawang",catMuzakki);
            CommonUser commonUser4 = new CommonUser("Caca","08988116753","caca@gmail.com","Karawang",catMuzakki);
            CommonUser commonUser5 = new CommonUser("Aep","08988116753","aep@gmail.com","Karawang",catMustahik);
            CommonUser commonUser6 = new CommonUser("Saeful","08988116753","saeful@gmail.com","Karawang",catMustahik);
            CommonUser commonUser7 = new CommonUser("Rere","08988116753","rere@gmail.com","Karawang",catMustahik);
            CommonUser commonUser8 = new CommonUser("Jupri","08988116753","jupri@gmail.com","Karawang",catMustahik);

            commonUserRepository.saveAll(Arrays.asList(commonUser1,commonUser2,commonUser3,commonUser4,commonUser5,commonUser6,commonUser7,commonUser8));
        }
        log.info("commonUserInit have been Completed");
    }

    private void transactionInit(){
        List<Transaction> transactions = transactionRepository.findAll();
        User admin = userRepository.findByUsername("admin");
        GlobalParam catTransPenerimaan = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_TRANSACTION", "PENERIMAAN");
        GlobalParam catTransPengeluaran = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_TRANSACTION", "PENGELUARAN");
        //Muzakki : Donatur
        Optional<CommonUser> muzakki1 = commonUserRepository.findById(1L);
        Optional<CommonUser> muzakki2 = commonUserRepository.findById(2L);
        Optional<CommonUser> muzakki3 = commonUserRepository.findById(3L);
        Optional<CommonUser> muzakki4 = commonUserRepository.findById(4L);
        //Mustahik : Penerima Donatur
        Optional<CommonUser> mustahik1 = commonUserRepository.findById(5L);
        Optional<CommonUser> mustahik2 = commonUserRepository.findById(6L);
        Optional<CommonUser> mustahik3 = commonUserRepository.findById(7L);
        Optional<CommonUser> mustahik4 = commonUserRepository.findById(8L);

        GlobalParam catDanaPenerimaan = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_DANA", "PENERIMAAN");
        GlobalParam catDanaPengeluaran = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_DANA", "PENGELUARAN");

        if (transactions.size() == 0 ){
            //PENERIMAAN
            Transaction penerimaan_dana_donatur1 = new Transaction(400000L, "Penerimaan Dana Donatur", admin, catTransPenerimaan, muzakki1.get(), catDanaPenerimaan);
            Transaction penerimaan_dana_donatur2 = new Transaction(200000L, "Penerimaan Dana Donatur", admin, catTransPenerimaan, muzakki2.get(), catDanaPenerimaan);
            Transaction penerimaan_dana_donatur3 = new Transaction(200000L, "Penerimaan Dana Donatur", admin, catTransPenerimaan, muzakki3.get(), catDanaPenerimaan);
            Transaction penerimaan_dana_donatur4 = new Transaction(200000L, "Penerimaan Dana Donatur", admin, catTransPenerimaan, muzakki4.get(), catDanaPenerimaan);
            transactionRepository.saveAll(Arrays.asList(penerimaan_dana_donatur1,penerimaan_dana_donatur2,penerimaan_dana_donatur3,penerimaan_dana_donatur4));

            this.journalMapSave(penerimaan_dana_donatur1);
            this.journalMapSave(penerimaan_dana_donatur2);
            this.journalMapSave(penerimaan_dana_donatur3);
            this.journalMapSave(penerimaan_dana_donatur4);
        }
    }

    private void journalMapSave(Transaction transaction){
        log.info("Journal Mapping start");
        GlobalParam catJournalPenerimaan = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_JOURNAL", "PENERIMAAN");
        GlobalParam catJournalPengeluaran = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_JOURNAL", "PENGELUARAN");
        GlobalParam accountKas = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_ACCOUNT", "KAS");
        GlobalParam accountBiaya = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_ACCOUNT", "BIAYA");
        GlobalParam accountPengeluaran = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_ACCOUNT", "PENGELUARAN");
        GlobalParam accountPenerimaan = globalParamRepository.findByParamConditionAndParamDesc("CATEGORY_ACCOUNT", "PENERIMAAN");
        if (transaction.getCategoryTransaction().getParamValue().equalsIgnoreCase("Penerimaan")){
            Journal debet = new Journal(accountKas.getParamValue(), transaction.getAmount(), 0L, catJournalPenerimaan, transaction);//DEBET
            Journal kredit = new Journal(accountBiaya.getParamValue(), 0L, transaction.getAmount(), catJournalPenerimaan, transaction);//KREDIT
            journalRepository.saveAll(Arrays.asList(debet, kredit));
            log.info("Save Journal Penerimaan is Success");
        }else{
            Journal debet = new Journal(accountBiaya.getParamValue(), transaction.getAmount(), 0L, catJournalPengeluaran, transaction);//DEBET
            Journal kredit = new Journal(accountKas.getParamValue(), 0L, transaction.getAmount(), catJournalPengeluaran, transaction);//KREDIT
            journalRepository.saveAll(Arrays.asList(debet,kredit));
            log.info("Save Journal Pengeluaran is Success");
        }
        log.info("Journal Mapping have been Completed");
    }
}
