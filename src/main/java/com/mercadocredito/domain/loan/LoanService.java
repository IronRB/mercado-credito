package com.mercadocredito.domain.loan;

import com.mercadocredito.domain.loan.input.LoanInput;
import com.mercadocredito.domain.loan.output.LoanOutput;
import com.mercadocredito.domain.target.ITargetRepository;
import com.mercadocredito.domain.target.Target;
import com.mercadocredito.domain.user.User;
import com.mercadocredito.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Pageable paging = PageRequest.of(pageNo+1, pageSize);

        Page<Loan> pagedResult = loanRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Loan>();
        }
    }

    public LoanOutput postLoan(LoanInput request){
        LoanOutput loanOutput = null;
        float rate = 0;
        User user = userRepository.findById(request.getUserId()).orElse(null);
        user.setCant(user.getCant() + 1);
        user.setAmountTotal(user.getAmountTotal() + request.getAmount());
        List<Target> targets = targetRepository.findAll();
        for (Target target: targets) {
            if(target.getCantMin()>= user.getCant() && target.getCantMax()<= user.getCant())
            {
                user.setTarget(target.getTarget());
                rate = target.getRate();
            }
        }
        userRepository.save(user);
        loanOutput.setInstallment(((rate/12)+(rate/12)/((float)Math.pow((1+(rate/12)),12-1)))* request.getAmount());
        return null;
    }



}
