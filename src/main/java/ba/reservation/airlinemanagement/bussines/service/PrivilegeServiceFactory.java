package ba.reservation.airlinemanagement.bussines.service;

public enum PrivilegeServiceFactory {


        PRIVILEGE_SERVICE(new PrivilegeService());
        private PrivilegeServiceLocal privilegeService;

        PrivilegeServiceFactory(PrivilegeServiceLocal privilegeServiceLocal) {
            this.privilegeService = privilegeServiceLocal;
        }


        public PrivilegeServiceLocal getPrivilegeService() {
            return privilegeService;
        }
    }

