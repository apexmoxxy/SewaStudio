package com.example.sewastudio.controller

import com.example.sewastudio.service.AuthService
import com.example.sewastudio.service.StudioService

class StudioController {
    companion object {
        private var studioService : StudioService = ClientController.getService(StudioService::class.java)

    }
}