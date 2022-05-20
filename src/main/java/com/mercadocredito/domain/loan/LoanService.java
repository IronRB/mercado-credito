package com.mercadocredito.domain.loan;

import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanOutput;
import com.mercadocredito.domain.target.ITargetRepository;
import com.mercadocredito.domain.target.Target;
import com.mercadocredito.domain.user.User;
import com.mercadocredito.domain.user.UserRepository;
import com.mercadocredito.utils.ArithmeticOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private ILoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ITargetRepository targetRepository;

    @Override
    public List<Loan> getLoans(String from, String to, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Loan> pagedResult = loanRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Loan>();
        }
    }

    public LoanOutput postLoan(LoanInput request){
        float rate = 0;
        User user = userRepository.findById(request.getUserId()).orElse(null);
        user.setCant(user.getCant() + 1);
        user.setAmountTotal(user.getAmountTotal() + request.getAmount());
        List<Target> targets = targetRepository.findAll();
        for (Target target: targets) {
            if(user.getCant() >= target.getCantMin() && user.getCant() <= target.getCantMax())
            {
                user.setTarget(target.getTarget());
                rate = target.getRate();
            }
        }
        userRepository.save(user);
        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setTerm(request.getTerm());
        loan.setUserId(user.getId());
        Date now = new Date();
        SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        loan.setDate(isoDate.format(now));

        loanRepository.save(loan);

        LoanOutput loanOutput = new LoanOutput((int)loan.getId(),ArithmeticOperation.calculateInstallment(rate/12,request.getTerm(),request.getAmount()));
        return loanOutput;
    }



}
