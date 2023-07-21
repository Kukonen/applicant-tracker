import {EducationForm, PaymentType} from "./candidate.ts";
import {responseParams} from "./response.ts";

export function convertEducationForm(educationForm: EducationForm) {
    switch (educationForm) {
        case "FULL_TIME":
            return 'Очное'
        case "MIXED":
            return 'Очно-заочное'
        case "EXTRAMURAL":
            return 'Заочное'
        default:
            return 'Что-то неизведоное'
    }
}

export function convertPaymentType(paymentType: PaymentType) {
    switch (paymentType) {
        case "BUDGET":
            return 'Бюджет'
        case "CONTRACT":
            return 'Контракт'
        case "TARGETED":
            return 'Целевое'
        default:
            return 'Что-то неизведоное'
    }
}

export function convertCertificateSubmitted(certificateSubmitted: boolean) {
    return certificateSubmitted ? 'Подано' : 'Не подано'
}

export function convertCandidate(res: responseParams) {
    if (res.status !== "ok") {
        return [];
    }

    let applicants: any[] = res.applicants;

    applicants = applicants.map(applicant => {
        applicant.paymentType = convertPaymentType(applicant.paymentType)
        applicant.priority = applicant.priority ? applicant.priority : 'Нет приоритета'
        applicant.educationForm = convertEducationForm(applicant.educationForm)
        applicant.certificateSubmitted = convertCertificateSubmitted(applicant.certificateSubmitted)

        return applicant
    })

    return applicants
}