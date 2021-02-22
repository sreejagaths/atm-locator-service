package com.mobiquity.atmloator.service;

import com.mobiquity.atmloator.model.response.ATMResponse;

public interface ATMLocatorService {
	ATMResponse fetchATMs();
	ATMResponse fetchATMsByCity(String city);
}
