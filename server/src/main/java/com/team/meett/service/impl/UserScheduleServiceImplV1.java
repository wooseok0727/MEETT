package com.team.meett.service.impl;

import com.team.meett.dto.UsRequestDto;
import com.team.meett.dto.UsResponseDto;
import com.team.meett.model.UserSchedule;
import com.team.meett.repository.UserScheduleRepository;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("UsService")
@RequiredArgsConstructor
public class UserScheduleServiceImplV1 implements UserScheduleService {

    //    @Autowired
    private final UserScheduleRepository UsRepository;

    @Override
    public List<UsResponseDto> selectAll() {
        return UsRepository.findAll().stream().map(UsResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UsResponseDto> findById(Long seq) {
        return UsRepository.findById(seq).map(UsResponseDto::new);
    }

    @Override
    public List<UsResponseDto> findByUsername(String username) {
        return UsRepository.findByUsername(username).stream().map(UsResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<UsResponseDto> findByTitle(String title) {
        return UsRepository.findByTitle(title).stream().map(UsResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public void insert(UsRequestDto userSchedule) {
        UsRepository.save(userSchedule.toEntity());
    }

    @Override
    public void update(UsRequestDto userSchedule, Long usSeq) {
        if(UsRepository.existsById(usSeq)){
            UsRepository.save(userSchedule.toEntity());
        }
    }

    @Override
    public int delete(Long seq) {
        //seq값이 존재하는지 체크 후 코드 실행
        if (UsRepository.existsById(seq)) {
            UsRepository.deleteById(seq);
        } else {
            //존재하지 않는 seq값일 경우 return 0
            return 0;
        }
        return 1;
    }


}
