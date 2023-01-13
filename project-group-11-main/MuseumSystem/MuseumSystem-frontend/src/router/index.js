import Vue from 'vue'
import Router from 'vue-router'
import Art from '@/components/Art'
import WelcomePage from '@/components/WelcomePage'
import Hello from '@/components/Hello'
import Loan from '@/components/Loan'
import RespondLoanRequest from '@/components/RespondLoanRequest'
import PurchaseTicket from '@/components/PurchaseTicket'
import ViewAllTickets from '@/components/ViewAllTickets'
import OwnerSetParameters from '@/components/OwnerSetParameters'
import AboutPage from '@/components/AboutPage'
import LogIn from '@/components/LogIn'
import CreateAccount from '@/components/CreateAccount'
import OwnerPortal from '@/components/OwnerPortal'
import EmployeePortal from '@/components/EmployeePortal'
import ArtManagement from '@/components/ArtManagement'
import ThankYou from '@/components/ThankYou'
import LoanReceived from '@/components/LoanReceived'
import EmployeeScheduleForEmployee from '@/components/EmployeeScheduleForEmployee'
import EmployeeScheduleForOwner from '@/components/EmployeeScheduleForOwner'
import ContactPage from '@/components/ContactPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/tickets',
      name: 'PurchaseTicket',
      component: PurchaseTicket
    },

    {
      path: '/ViewAllTickets',
      name: 'ViewTickets',
      component: ViewAllTickets
    },

    {
      path: '/about',
      name: 'AboutPage',
      component: AboutPage
    },

    {
      path: '/managemuseum',
      name: 'OwnerSetParameters',
      component: OwnerSetParameters
    },

    {
      path: '/createAccount',
      name: 'createAccount',
      component: CreateAccount
    },

    {
      path: '/login',
      name: 'LogIn',
      component: LogIn
    },

    {
      path: '/app',
      name: 'WelcomePage',
      component: WelcomePage
    },
    {
      path: '/',
      name: 'WelcomePage',
      component: WelcomePage
    },
    {
      path: '/art',
      name: 'Art',
      component: Art
    },
    {
      path: '/employeemanagement',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/loan',
      name: 'MuseumManagement_visitorloan',
      component: Loan
    },
    {
      path: '/PendingLoanRequest',
      name: 'RespondLoanRequest',
      component: RespondLoanRequest
    },
    {
      path: '/ownerportal',
      name: 'OwnerPortal',
      component: OwnerPortal
    },
    {
      path: '/employeeportal',
      name: 'EmployeePortal',
      component: EmployeePortal
    },
    {
      path: '/artmanagement',
      name: 'ArtManagement',
      component: ArtManagement
    },
    {
      path: '/thankyou',
      name: 'ThankYou',
      component: ThankYou
    },
    {
      path: '/loanreceived',
      name: 'LoanReceived',
      component: LoanReceived
    },
    {
      path: '/viewschedule',
      name: 'EmployeeScheduleForEmployee',
      component: EmployeeScheduleForEmployee
    },
    {
      path: '/makeschedule',
      name: 'EmployeeScheduleForOwner',
      component: EmployeeScheduleForOwner
    },
    {
      path: '/contact',
      name: 'Contact',
      component: ContactPage
    }
  ]
})
