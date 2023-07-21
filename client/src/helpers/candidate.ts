export type EducationForm =
    'FULL_TIME' | /** Очная */
    'EXTRAMURAL' | /** Заочная */
    'MIXED'; /** Очно-заочная */
export type PaymentType =
    'BUDGET' | /** Бюджетное место */
    'CONTRACT' | /** Контрактное место */
    'TARGETED'; /** Целевое место */

export interface Candidate {
    university: string,
    programPlaces: number,
    programName: string,
    educationForm: EducationForm,
    paymentType: PaymentType,
    score: number,
    priority?: number,
    certificateSubmitted: boolean
}